package com.example.catcompendium.api

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Abstract Base Data source class with error handling
 */
interface BaseDataSource {
    suspend fun <T> getResult(call: suspend () -> Response<T>): APIResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return APIResult.Success(body)
            }
            return error(" ${response.errorBody()?.deserialize<ErrorBody>()?.message}")
        } catch (e: Exception) {

            return when(e) {
                /** Check using connectivity manager if this exception is because of the network status */
                is UnknownHostException -> error("No internet connection")
                is SocketTimeoutException -> error("Please check your connectivity")
                else -> error(call().errorBody()?.string() ?: e.message ?: "Something went wrong!")
            }
        }
    }

    private fun <T> error(message: String): APIResult<T> {
        /** We can deserialize error model (in case we get error msg from server)
         * and pass the message */

        return APIResult.Error( "$message")
    }
}