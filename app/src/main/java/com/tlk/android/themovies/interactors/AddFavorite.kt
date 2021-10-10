package com.tlk.android.themovies.interactors

import com.tlk.android.themovies.data.db.entities.FavoriteEntity
import com.tlk.android.themovies.data.repositories.MovieRepository
import javax.inject.Inject

class AddFavorite @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(favoriteEntity: FavoriteEntity) = movieRepository.addFavorite(favoriteEntity)
}