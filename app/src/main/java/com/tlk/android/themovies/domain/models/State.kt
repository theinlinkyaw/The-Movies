package com.tlk.android.themovies.domain.models

import java.lang.Exception

sealed class State<out T> {

    data class Success<out T>(val data: T) : State<T>()

    data class Error<out T>(val exception: Exception, val data: T?) : State<T>()

}