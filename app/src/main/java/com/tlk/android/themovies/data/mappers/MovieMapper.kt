package com.tlk.android.themovies.data.mappers

import com.tlk.android.themovies.data.api.reponses.Movie
import com.tlk.android.themovies.data.db.entities.MovieEntity
import javax.inject.Inject

class MovieMapper @Inject constructor() {
    fun toMovieEntity(movies: List<Movie>): List<MovieEntity> {
        return movies.map { m ->
            MovieEntity(
                m.id,
                m.title,
                "https://image.tmdb.org/t/p/original" + m.poster_path,
                "https://image.tmdb.org/t/p/original" + m.backdrop_path,
                m.overview,
                m.release_date,
                m.popularity,
                m.vote_count,
                m.vote_average
            )
        }
    }
}