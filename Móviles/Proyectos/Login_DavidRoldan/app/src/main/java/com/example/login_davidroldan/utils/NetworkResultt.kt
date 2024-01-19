package com.example.login_davidroldan.utils


sealed class NetworkResultt<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResultt<T>(data)

    class Error<T>(message: String, data: T? = null) : NetworkResultt<T>(data, message)



    fun <R> map( transform :(data: T?) -> R) : NetworkResultt<R> =
        when(this){
            is Error -> Error(message!!,transform(data))
            is Success -> Success(transform(data))
        }
}