package com.ae.tabbedmovies.ui.upcoming.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ae.tabbedmovies.data.service.MoviesServices
import com.ae.tabbedmovies.dto.MoviesResponse
import com.ae.tabbedmovies.dto.Resource
import kotlinx.coroutines.Dispatchers

class UpComingViewModel(private val moviesServices: MoviesServices): ViewModel() {
    val upcoming: LiveData<Resource<MoviesResponse>> = liveData(Dispatchers.IO) {
        val result = moviesServices.getUpcoming()
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(result))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}