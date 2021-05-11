package com.example.moviesapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SingleMovie (
    val id: String,
    val title: String,
    val year: String,
    val genres: List<String>,
    val ratings: List<Long>,
    val poster: String,
    val contentRating: String,
    val duration: String,
    val releaseDate: String,
    val averageRating: Long,
    val originalTitle: String,
    val storyline: String,
    val actors: List<String>,
    val imdbRating: String,
    val posterurl: String
): Parcelable

data class MapOfMovies(
    val map: HashMap<String, SingleMovie>
)