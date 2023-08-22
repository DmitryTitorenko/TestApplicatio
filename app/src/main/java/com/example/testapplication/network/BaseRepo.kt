package com.example.testapplication.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class BaseRepo {
    fun <T> handleRequest(response: Response<T>): Result<T> {
        return try {
            val body = response.body()

            if (response.isSuccessful && body != null) {
                return Result.Success(body)
            }

            val code = response.code()
            val errorBody = response.errorBody()?.toString()

            val message = errorBody?.let { message ->
                try {
                    JSONObject(message).getString("status_message")
                } catch (e: JSONException) {
                    null
                }
            }
            val statusCode = errorBody?.let { statusCode ->
                try {
                    JSONObject(statusCode).getInt("status_code")
                } catch (e: JSONException) {
                    null
                }
            }

            val apiError = ApiError(
                errorCode = code, statusMessage = message, statusCode = statusCode
            )
            return Result.Failure(apiError)
        } catch (e: IOException) {
            Result.Exception(e)
        } catch (e: HttpException) {
            Result.Exception(e)
        }
    }
}
