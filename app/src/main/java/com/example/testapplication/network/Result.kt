package com.example.testapplication.network

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure<T>(val apiError: ApiError) : Result<T>()
    class Exception<T>(val exception: Throwable) : Result<T>()
    object Loading : Result<Nothing>()
}