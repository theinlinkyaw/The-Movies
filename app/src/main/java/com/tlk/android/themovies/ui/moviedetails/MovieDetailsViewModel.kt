package com.tlk.android.themovies.ui.moviedetails

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlk.android.themovies.data.db.entities.FavoriteEntity
import com.tlk.android.themovies.domain.models.MovieWithFavorite
import com.tlk.android.themovies.interactors.AddFavorite
import com.tlk.android.themovies.interactors.RemoveFavorite
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
) : ViewModel() {

    fun toggleFavorite(movieWithFavorite: MovieWithFavorite) {
        val favorite = FavoriteEntity(movieWithFavorite.id)

        viewModelScope.launch {
            if (movieWithFavorite.favorite) {
                addFavorite(favorite)
            } else {
                removeFavorite(favorite)
            }
        }
    }
}