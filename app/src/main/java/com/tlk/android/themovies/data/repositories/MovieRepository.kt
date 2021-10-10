package com.tlk.android.themovies.data.repositories

import com.tlk.android.themovies.data.db.entities.FavoriteEntity
import com.tlk.android.themovies.data.db.entities.MovieEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    suspend fun addFavorite(favoriteEntity: FavoriteEntity) = movieLocalDataSource.addFavorite(favoriteEntity)

    suspend fun removeFavorite(favoriteEntity: FavoriteEntity) = movieLocalDataSource.removeFavorite(favoriteEntity)

    suspend fun getRemoteUpcoming() = movieRemoteDataSource.getUpcoming()

    suspend fun getRemotePopular() = movieRemoteDataSource.getPopular()

    suspend fun getUpcoming() = movieLocalDataSource.getUpcoming()

    suspend fun getPopular() = movieLocalDataSource.getPopular()

    suspend fun updatePopular(movieEntities: List<MovieEntity>) = movieLocalDataSource.updatePopular(movieEntities)

    suspend fun updateUpcoming(movieEntities: List<MovieEntity>) = movieLocalDataSource.updateUpcoming(movieEntities)
}