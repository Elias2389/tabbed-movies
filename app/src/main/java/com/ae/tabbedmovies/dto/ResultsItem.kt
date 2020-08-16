package com.ae.tabbedmovies.dto


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultsItem(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @SerializedName("original_title")
    var originalTitle: String = "",
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("backdrop_path")
    var backdropPath: String? = null ,
    @SerializedName("release_date")
    var releaseDate: String = "",
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("vote_count")
    var voteCount: Int = 0
): Serializable

