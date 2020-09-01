package com.ae.tabbedmovies.data.networkboundresource

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.ae.tabbedmovies.dto.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor() {
    private val result = MediatorLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

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

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource, Observer { newData ->
            result.value = Resource.loading(newData)
        })

        CoroutineScope(coroutineContext).launch(Dispatchers.Main) {
            try {
                val response = createCall()
                result.removeSource(dbSource)
                saveResultAndReInit(response)
            } catch (error: Exception) {
                onFetchFailed()
                result.removeSource(dbSource)
                result.addSource(dbSource, Observer { newData ->
                    result.value = Resource.error(data = newData, message = error.message ?: "Error Occurred!")
                })
            }
        }

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

    @NonNull
    @MainThread
    protected abstract suspend fun createCall(): RequestType

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

