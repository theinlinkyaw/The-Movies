package com.tlk.android.themovies.interactors

import com.tlk.android.themovies.data.repositories.MovieRepository
import javax.inject.Inject

class GetUpcoming @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() = movieRepository.getUpcoming()
}