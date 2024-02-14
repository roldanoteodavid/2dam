package com.example.apollo_davidroldan.data.sources.remote.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.CustomScalarType
import com.example.apollo_davidroldan.common.Constantes
import com.example.apollo_davidroldan.data.DateAdapter
import com.example.apollo_davidroldan.data.sources.remote.LoginService
import com.example.apollo_davidroldan.utils.Constants.BASELOGIN_URL
import com.example.apollo_davidroldan.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterMoshiFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
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
            .baseUrl(BASELOGIN_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun createApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .addCustomScalarAdapter(CustomScalarType.Date, DateAdapter())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)
}
