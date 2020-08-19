package com.arivas.moviesappkotlin.common.network.networkboundresource

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.ae.tabbedmovies.dto.Resource


abstract class NetworkBoundResource<ResultType, RequestType> {
    @MainThread constructor()
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource: LiveData<ResultType> = loadFromDb()
        result.addSource(dbSource, Observer { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource, Observer {newData ->
                    result.value = Resource.success(newData)
                })
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource, Observer { newData ->
            result.value = Resource.loading(newData)
        })

//        createCall()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({ response ->
//                result.removeSource(dbSource)
//                saveResultAndReInit(response)
//            },{ error ->
//                onFetchFailed()
//                result.removeSource(dbSource)
//                result.addSource(dbSource, Observer { newData ->
//                    result.value = Resource.error(error.message.toString(), newData)
//                })
//            })

    }

    @MainThread
    @SuppressLint("StaticFieldLeak")
    private fun saveResultAndReInit(response: RequestType) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                saveCallResult(response)
                return null
            }

            override fun onPostExecute(void: Void?) {
                result.addSource(loadFromDb(), Observer { newData ->
                    result.value = Resource.success(newData)
                })
            }
        }.execute()

    }


    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

//    @NonNull
//    @MainThread
//    protected abstract fun createCall(): Observable<RequestType>

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
     protected fun shouldFetch(@Nullable data: ResultType): Boolean {
        return true
    }

    @MainThread
    protected fun onFetchFailed() {
    }

    fun getAsLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

}

