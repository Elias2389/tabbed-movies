package com.ae.tabbedmovies.dto

import com.ae.tabbedmovies.entity.ResultsItemEntity
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results: List<ResultsItemEntity>,
    @SerializedName("total_results")
    val totalResults: Int = 0
)