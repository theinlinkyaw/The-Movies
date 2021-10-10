package com.tlk.android.themovies.data.api.reponses

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String,
    val release_date: String,
    val popularity: Float,
    val vote_count: Int,
    val vote_average: Float
)
