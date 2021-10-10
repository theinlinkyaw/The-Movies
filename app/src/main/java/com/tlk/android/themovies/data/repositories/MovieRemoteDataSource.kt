package com.tlk.android.themovies.data.repositories

import com.tlk.android.themovies.BuildConfig
import com.tlk.android.themovies.data.api.ApiService
import com.tlk.android.themovies.data.api.reponses.Popular
import com.tlk.android.themovies.data.api.reponses.Upcoming
import com.tlk.android.themovies.data.db.entities.MovieEntity
import com.tlk.android.themovies.data.mappers.MovieMapper
import com.tlk.android.themovies.domain.models.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService, private val movieMapper: MovieMapper) {

    suspend fun getUpcoming(): State<List<MovieEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUpcoming(BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    response.body()!!.let {
                        State.Success(movieMapper.toMovieEntity(it.results))
                    }
                } else {
                    State.Error(Exception(response.message()), null)
                }
            } catch (e: Exception) {
                when(e) {
                    is SocketTimeoutException ->
                        State.Error(Exception("Connection Error!"), null)
                    is ConnectException,
                    is UnknownHostException ->
                        State.Error(Exception("No Internet Connection!"), null)
                    else -> throw e
                }
            }
        }

    suspend fun getPopular(): State<List<MovieEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPopular(BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    response.body()!!.let {
                        State.Success(movieMapper.toMovieEntity(it.results))
                    }
                } else {
                    State.Error(Exception(response.message()), null)
                }
            } catch (e: Exception) {
                when(e) {
                    is SocketTimeoutException ->
                        State.Error(Exception("Connection Error!"), null)
                    is ConnectException,
                    is UnknownHostException ->
                        State.Error(Exception("No Internet Connection!"), null)
                    else -> throw e
                }
            }
        }
}