package com.gugugu.remote.util

import android.util.Log
import com.google.gson.Gson
import com.gugugu.domain.exception.NotFoundException
import com.gugugu.domain.exception.ServerDownException
import com.gugugu.domain.exception.UnknownException
import com.gugugu.domain.exception.UnknownHttpException
import com.gugugu.remote.response.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

suspend inline fun <T> guguguApiCall(
    crossinline callFunction: suspend () -> T
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: HttpException) {
        val message = getErrorResponse(e)
        throw when (e.code()) {
            404 -> NotFoundException(
                message = message
            )

            503 -> ServerDownException(
                message = message
            )
            else -> UnknownHttpException(
                message = message
            )
        }
    } catch (e: Exception) {
        Log.d("LOG", "guguguApiCall: $e")
        throw UnknownException(
            message = e.message.toString()
        )
    }

}

private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 에러가 발생했습니다."

fun getErrorResponse(e: HttpException): String {
    val errorStr = e.response()?.errorBody()?.string()
    val errorDto = Gson().fromJson(
        errorStr, ErrorResponse::class.java
    )
    return errorDto.message ?: UNKNOWN_ERROR_MESSAGE
}
