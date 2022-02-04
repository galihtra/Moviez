package com.galih.moviez.data.source.remote.vo

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {

    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> error(message: String?, body: T): ApiResponse<T> =
            ApiResponse(StatusResponse.ERROR, body, message)
    }

}