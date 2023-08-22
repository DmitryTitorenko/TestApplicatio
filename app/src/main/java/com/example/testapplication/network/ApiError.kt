package com.example.testapplication.network

data class ApiError(
    val errorCode: Int? = null,
    val statusCode: Int? = null,
    val statusMessage: String?
)