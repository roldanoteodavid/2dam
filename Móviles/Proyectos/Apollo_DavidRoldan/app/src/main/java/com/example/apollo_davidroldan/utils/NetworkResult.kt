package com.example.apollo_davidroldan.utils


sealed class NetworkResult<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)



    fun <R> map( transform :(data: T?) -> R) : NetworkResult<R> =
        when(this){
            is Error -> Error(message!!,transform(data))
            is Success -> Success(transform(data))
        }
}