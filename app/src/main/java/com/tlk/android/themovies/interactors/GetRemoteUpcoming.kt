package com.tlk.android.themovies.interactors

import com.tlk.android.themovies.data.repositories.MovieRepository
import javax.inject.Inject

class GetRemoteUpcoming @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() = movieRepository.getRemoteUpcoming()
}