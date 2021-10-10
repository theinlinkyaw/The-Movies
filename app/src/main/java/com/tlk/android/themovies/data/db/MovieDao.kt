package com.tlk.android.themovies.data.db

import androidx.room.*
import com.tlk.android.themovies.data.db.entities.MovieEntity
import com.tlk.android.themovies.data.db.entities.PopularEntity
import com.tlk.android.themovies.data.db.entities.UpcomingEntity
import com.tlk.android.themovies.domain.models.MovieWithFavorite

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT movies.id, " +
            "title, posterPath, backdropPath, overview, releaseDate, popularity, voteCount, voteAverage, " +
            "(CASE WHEN favorites.movieId IS NULL THEN 0 ELSE 1 END) AS favorite " +
            "FROM movies " +
            "INNER JOIN upcoming ON upcoming.movieId = movies.id " +
            "LEFT JOIN favorites ON favorites.movieId = movies.id")
    suspend fun getUpcoming() : List<MovieWithFavorite>

    @Query("SELECT movies.id, " +
            "title, posterPath,backdropPath, overview, releaseDate, popularity, voteCount, voteAverage, " +
            "(CASE WHEN favorites.movieId IS NULL THEN 0 ELSE 1 END) AS favorite " +
            "FROM movies " +
            "INNER JOIN upcoming ON upcoming.movieId = movies.id " +
            "LEFT JOIN favorites ON favorites.movieId = movies.id")
    suspend fun getPopular() : List<MovieWithFavorite>

    @Query("DELETE FROM movies " +
            "WHERE id IN (SELECT movieId FROM popular)")
    suspend fun removeAllPopular()

    @Query("DELETE FROM movies " +
            "WHERE id IN (SELECT movieId FROM upcoming)")
    suspend fun removeAllUpcoming()

}