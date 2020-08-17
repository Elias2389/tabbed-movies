package com.ae.tabbedmovies.ui.topdated.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ae.tabbedmovies.data.service.MoviesServices
import com.ae.tabbedmovies.dto.MoviesResponse
import com.ae.tabbedmovies.dto.Resource
import kotlinx.coroutines.Dispatchers

class TopRatedViewModel(private val moviesServices: MoviesServices): ViewModel() {

    val topRatedMovies: LiveData<Resource<MoviesResponse>> = liveData(Dispatchers.IO) {
        val result = moviesServices.getTopRated()
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(result))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}