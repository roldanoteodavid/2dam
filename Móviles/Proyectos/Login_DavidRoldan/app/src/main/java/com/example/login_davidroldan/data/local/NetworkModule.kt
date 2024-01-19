package com.example.login_davidroldan.data.local

import android.content.Context
import androidx.room.Room
import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.data.dao.CustomerDao
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): AutoresRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            AutoresRoomDatabase::class.java,
            Constantes.APP_DB
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideAutorDao(autoresRoomDatabase: AutoresRoomDatabase):AutorDao{
        return autoresRoomDatabase.autorDao()
    }

    @Provides
    fun provideLibroDao(autoresRoomDatabase: AutoresRoomDatabase):LibroDao{
        return autoresRoomDatabase.libroDao()
    }

    @Provides
    fun provideCustomerDao(autoresRoomDatabase: AutoresRoomDatabase): CustomerDao {
        return autoresRoomDatabase.customerDao()
    }
}