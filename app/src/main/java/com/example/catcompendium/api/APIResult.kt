package com.example.catcompendium.api

sealed class APIResult<out T> {
    data class Success<out T>(val data : T?) : APIResult<T>()
    data class Error<out T>(val message: String? = "Something went wrong", val data: T? = null): APIResult<T>()
}
