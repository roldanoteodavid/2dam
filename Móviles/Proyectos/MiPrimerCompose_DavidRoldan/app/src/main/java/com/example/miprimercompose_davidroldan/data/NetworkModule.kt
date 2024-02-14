package com.example.miprimercompose_davidroldan.data

import android.content.Context
import androidx.room.Room
import com.example.miprimercompose_davidroldan.common.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SimpsonsRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            SimpsonsRoomDatabase::class.java,
            Constantes.APP_DB
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideSimpsonDao(simpsonsRoomDatabase: SimpsonsRoomDatabase): SimpsonDao {
        return simpsonsRoomDatabase.simpsonDao()
    }
}