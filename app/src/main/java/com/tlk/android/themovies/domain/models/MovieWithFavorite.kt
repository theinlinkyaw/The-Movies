package com.tlk.android.themovies.domain.models

import java.io.Serializable

data class MovieWithFavorite(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float,
    var favorite: Boolean,
) : Serializable
