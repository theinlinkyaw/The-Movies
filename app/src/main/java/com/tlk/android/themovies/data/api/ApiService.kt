package com.tlk.android.themovies.data.api

import com.tlk.android.themovies.data.api.reponses.Popular
import com.tlk.android.themovies.data.api.reponses.Upcoming
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("api_key") key: String): Response<Upcoming>

    @GET("movie/popular")
    suspend fun getPopular(@Query("api_key") key: String): Response<Popular>
}