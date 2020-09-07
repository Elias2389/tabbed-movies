package com.ae.tabbedmovies.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class ResultsItemEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String = "",
    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    var originalLanguage: String = "",
    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    var originalTitle: String = "",
    @SerializedName("video")
    @ColumnInfo(name = "video")
    var video: Boolean = false,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String = "",
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String = "",
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null ,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String = "",
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    var adult: Boolean = false,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Int = 0
): Serializable

