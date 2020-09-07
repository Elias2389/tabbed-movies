package com.ae.tabbedmovies.ui.popularmovies.viewmodel

import androidx.lifecycle.*
import com.ae.tabbedmovies.data.db.MoviesDao
import com.ae.tabbedmovies.data.networkboundresource.NetworkBoundResource
import com.ae.tabbedmovies.data.service.MoviesServices
import com.ae.tabbedmovies.dto.MoviesResponse
import com.ae.tabbedmovies.data.networkboundresource.Resource
import com.ae.tabbedmovies.entity.ResultsItemEntity
import java.lang.RuntimeException

class PopularMoviesViewModel(private val moviesServices: MoviesServices,
                             private val moviesDao: MoviesDao
): ViewModel() {

    val popularMovies: LiveData<Resource<List<ResultsItemEntity>>> =
        object : NetworkBoundResource<List<ResultsItemEntity>, MoviesResponse>() {
            override fun loadFromDb(): LiveData<List<ResultsItemEntity>> {
                return moviesDao.getMoviesFromDatabase()
            }

            override suspend fun createCall(): MoviesResponse {
                return moviesServices.getPopularMovies()
            }

            override fun saveCallResult(item: MoviesResponse) {
                try {
                    moviesDao.insertMoviesToDatabase(item.results)
                } catch (e: RuntimeException) {
                    e.stackTrace
                }
            }

            override fun shouldFetch(data: List<ResultsItemEntity>): Boolean {
                return data == null || data.isEmpty()
            }


        }.getAsLiveData()

}