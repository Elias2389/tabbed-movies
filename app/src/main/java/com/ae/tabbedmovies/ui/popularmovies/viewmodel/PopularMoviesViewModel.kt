package com.ae.tabbedmovies.ui.popularmovies.viewmodel

import androidx.lifecycle.*
import com.ae.tabbedmovies.data.service.MoviesServices
import com.ae.tabbedmovies.dto.MoviesResponse
import com.ae.tabbedmovies.dto.Resource
import kotlinx.coroutines.Dispatchers

class PopularMoviesViewModel(private val moviesServices: MoviesServices): ViewModel() {
    val popularMovies: LiveData<Resource<MoviesResponse>> = liveData(Dispatchers.IO) {
        val result = moviesServices.getPopularMovies()
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(result))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    val latest: LiveData<Resource<MoviesResponse>> = liveData {
        val result = moviesServices.getLatestMovie()
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(result))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}