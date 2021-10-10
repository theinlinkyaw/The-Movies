package com.tlk.android.themovies.data.repositories

import com.tlk.android.themovies.data.db.*
import com.tlk.android.themovies.data.db.entities.FavoriteEntity
import com.tlk.android.themovies.data.db.entities.MovieEntity
import com.tlk.android.themovies.data.db.entities.PopularEntity
import com.tlk.android.themovies.data.db.entities.UpcomingEntity
import com.tlk.android.themovies.domain.models.MovieWithFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao, private val movieDao: MovieDao, private val popularDao: PopularDao, private val upcomingDao: UpcomingDao) {

    suspend fun addFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.addFavorite(favoriteEntity)

    suspend fun removeFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.removeFavorite(favoriteEntity)

    suspend fun getUpcoming() = movieDao.getUpcoming()

    suspend fun getPopular() = movieDao.getPopular()

    suspend fun updatePopular(movieEntities: List<MovieEntity>): List<MovieWithFavorite> =
        withContext(Dispatchers.IO) {
            val popular = movieEntities.map { m -> PopularEntity(movieId = m.id) }
            movieDao.removeAllPopular()
            movieDao.addMovies(movieEntities)
            popularDao.addPopular(popular)
            movieDao.getPopular()
        }

    suspend fun updateUpcoming(movieEntities: List<MovieEntity>): List<MovieWithFavorite> =
        withContext(Dispatchers.IO) {
            val upcoming = movieEntities.map { m -> UpcomingEntity(movieId = m.id) }
            movieDao.removeAllUpcoming()
            movieDao.addMovies(movieEntities)
            upcomingDao.addUpcoming(upcoming)
            movieDao.getUpcoming()
        }
}