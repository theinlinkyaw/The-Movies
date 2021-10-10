package com.tlk.android.themovies.interactors

import com.tlk.android.themovies.data.db.entities.MovieEntity
import com.tlk.android.themovies.data.repositories.MovieRepository
import javax.inject.Inject

class RefreshPopular @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieEntities: List<MovieEntity>) = movieRepository.updatePopular(movieEntities)
}