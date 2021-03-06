package com.example.moviecatalogbylatiefniam.data.source.remote.response

import com.example.moviecatalogbylatiefniam.data.source.remote.StatusResponse

class ApiResponse<T>(val status: StatusResponse,
                     val body: T,
                     val message: String?) {
    companion object{
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)
        fun <T> empty(message: String, body: T):ApiResponse<T> = ApiResponse(StatusResponse.EMPTY, body, message)
        fun <T> error (message: String, body: T): ApiResponse <T> = ApiResponse(StatusResponse.ERROR,body, message)
    }
}