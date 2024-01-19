package com.example.login_davidroldan.data.sources.remote.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.data.sources.remote.AuthAuthenticator
import com.example.login_davidroldan.data.sources.remote.AuthInterceptor
import com.example.login_davidroldan.data.sources.remote.CustomerService
import com.example.login_davidroldan.data.sources.remote.LoginService
import com.example.login_davidroldan.data.sources.remote.OrderService
import com.example.login_davidroldan.data.sources.remote.ServiceInterceptor
import com.example.login_davidroldan.utils.Constants.BASE_URL
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideServiceInterceptor(): ServiceInterceptor = ServiceInterceptor()


    @Singleton
    @Provides
    fun provideHttpClient(
        serviceInterceptor: ServiceInterceptor,
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(serviceInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterMoshiFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(LocalDateAdapter())
            .add(LocalDateTimeAdapter())
            .add(UuidAdapter())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constantes.DATA_STORE)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CustomerService =
        retrofit.create(CustomerService::class.java)

    @Singleton
    @Provides
    fun provideOrderService(retrofit: Retrofit): OrderService =
        retrofit.create(OrderService::class.java)

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)
}


class LocalDateAdapter {

    @ToJson
    fun toJson(value: LocalDate): String {
        return value.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @FromJson
    fun fromJson(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}

class LocalDateTimeAdapter {

    @ToJson
    fun toJson(value: LocalDateTime): String {
        return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    @FromJson
    fun fromJson(value: String): LocalDateTime {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }
}

class UuidAdapter {

    @ToJson
    fun toJson(value: UUID): String {
        return value.toString()
    }

    @FromJson
    fun fromJson(value: String): UUID {
        return UUID.fromString(value)
    }
}