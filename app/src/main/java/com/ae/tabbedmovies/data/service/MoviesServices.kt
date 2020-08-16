package com.ae.tabbedmovies.data.service

import com.ae.tabbedmovies.dto.MoviesResponse
import retrofit2.http.GET


interface MoviesServices {

    /**
     * Method to get popular movies from API REST
     *
     * @return response with popular movies
     */
    @GET("popular")
    suspend fun getPopularMovies(): MoviesResponse

    /**
     * Method to get latest movies from API REST
     *
     * @return response with latest movie
     */
    @GET("upcoming")
    suspend fun getLatestMovie(): MoviesResponse
}