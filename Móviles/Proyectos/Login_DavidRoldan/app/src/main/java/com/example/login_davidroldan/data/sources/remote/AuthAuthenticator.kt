package com.example.login_davidroldan.data.sources.remote

import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.utils.Constants.BASE_URL
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            val newAccessToken = newToken.headers()[Constantes.AUTHORIZATION]

            newAccessToken?.let {
                tokenManager.saveAccessToken(it)
            }
            response.request.newBuilder()
                .header(Constantes.AUTHORIZATION, Constantes.BEARER+newAccessToken)
                .build()
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<Unit> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(LoginService::class.java)
        return service.refreshToken("$refreshToken")
    }
}