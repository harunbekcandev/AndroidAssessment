package com.harunbekcan.androidassessment.utils

sealed interface Resource<out T : Any> {
    data object Loading : Resource<Nothing>
    data class Success<out T : Any>(val data: T) : Resource<T>
    data class Error(val errorMessage: String?) : Resource<Nothing>
}