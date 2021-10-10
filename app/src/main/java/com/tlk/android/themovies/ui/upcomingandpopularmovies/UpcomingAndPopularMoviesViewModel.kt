package com.tlk.android.themovies.ui.upcomingandpopularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlk.android.themovies.data.db.entities.FavoriteEntity
import com.tlk.android.themovies.domain.models.MovieWithFavorite
import com.tlk.android.themovies.domain.models.State
import com.tlk.android.themovies.interactors.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpcomingAndPopularMoviesViewModel @Inject constructor(
    private val addFavorite: AddFavorite,
    private val getPopular: GetPopular,
    private val getRemotePopular: GetRemotePopular,
    private val getRemoteUpcoming: GetRemoteUpcoming,
    private val getUpcoming: GetUpcoming,
    private val refreshPopular: RefreshPopular,
    private val refreshUpcoming: RefreshUpcoming,
    private val removeFavorite: RemoveFavorite
) : ViewModel() {

    private val _upcoming = MutableLiveData<List<MovieWithFavorite>>()
    val upcoming: LiveData<List<MovieWithFavorite>> = _upcoming

    private val _popular = MutableLiveData<List<MovieWithFavorite>>()
    val popular: LiveData<List<MovieWithFavorite>> = _popular

    private val _error = MutableLiveData<String> ()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun getUpcomingMovies() {
        viewModelScope.launch {
            _loading.postValue(true)
            _upcoming.postValue(getUpcoming())
            when(val state = getRemoteUpcoming()) {
                is State.Success -> {
                    _upcoming.postValue(refreshUpcoming(state.data))
                    _loading.postValue(false)
                }
                is State.Error -> {
                    _loading.postValue(false)
                    _error.postValue(state.exception.message)
                }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            _loading.postValue(true)
            _popular.postValue(getPopular())
            when(val state = getRemotePopular()) {
                is State.Success -> {
                    _popular.postValue(refreshPopular(state.data))
                    _loading.postValue(false)
                }
                is State.Error -> {
                    _loading.postValue(false)
                    _error.postValue(state.exception.message)
                }
            }
        }
    }

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