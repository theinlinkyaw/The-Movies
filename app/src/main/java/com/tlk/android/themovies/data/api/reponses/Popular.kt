package com.tlk.android.themovies.data.api.reponses

import com.squareup.moshi.Json

data class Popular(
    val page: Int,
    val results: List<Movie>
)