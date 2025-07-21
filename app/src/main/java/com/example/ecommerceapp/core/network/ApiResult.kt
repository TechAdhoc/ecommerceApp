package com.example.ecommerceapp.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Base API result wrapper class to handle network responses
 */
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(
        val message: String,
        val code: Int? = null,
        val errorType: ErrorType = ErrorType.UNKNOWN
    ) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
    object Initial : ApiResult<Nothing>()
    
    /**
     * Enum to categorize different types of errors
     */
    enum class ErrorType {
        NETWORK,
        TIMEOUT,
        API_ERROR,
        AUTH_ERROR,
        UNKNOWN
    }
}

/**
 * Utility function to safely handle API calls and wrap responses in ApiResult
 */
fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Loading)
    
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ApiResult.Success(it))
            } ?: emit(ApiResult.Error(
                "Response body is null", 
                errorType = ApiResult.ErrorType.API_ERROR
            ))
        } else {
            val errorType = when(response.code()) {
                401, 403 -> ApiResult.ErrorType.AUTH_ERROR
                else -> ApiResult.ErrorType.API_ERROR
            }
            
            emit(ApiResult.Error(
                "API call failed with code: ${response.code()}", 
                response.code(), 
                errorType
            ))
        }
    } catch (e: UnknownHostException) {
        emit(ApiResult.Error(
            "No internet connection. Please check your network settings.",
            errorType = ApiResult.ErrorType.NETWORK
        ))
    } catch (e: ConnectException) {
        emit(ApiResult.Error(
            "Failed to connect to server. Please check your network.",
            errorType = ApiResult.ErrorType.NETWORK
        ))
    } catch (e: SocketTimeoutException) {
        emit(ApiResult.Error(
            "Connection timed out. Please try again.",
            errorType = ApiResult.ErrorType.TIMEOUT
        ))
    } catch (e: IOException) {
        emit(ApiResult.Error(
            "Network error: ${e.message}",
            errorType = ApiResult.ErrorType.NETWORK
        ))
    } catch (e: Exception) {
        emit(ApiResult.Error(
            "Error: ${e.message}",
            errorType = ApiResult.ErrorType.UNKNOWN
        ))
    }
}
