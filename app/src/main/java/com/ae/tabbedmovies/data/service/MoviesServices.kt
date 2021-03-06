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
    suspend fun getUpcoming(): MoviesResponse

    /**
     * Method to get the top rated movies from API REST
     *
     * @return response with top rated movie
     */
    @GET("top_rated")
    suspend fun getTopRated(): MoviesResponse
}