package com.example.login_davidroldan.data.sources.remote

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.data.sources.remote.di.NetworkModule.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject



class TokenManager @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private val refreshtoken = stringPreferencesKey(Constantes.REFRESHTOKEN)
        private val accessToken = stringPreferencesKey(Constantes.ACCESS_TOKEN)
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[refreshtoken]
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[refreshtoken] = token
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(refreshtoken)
        }
    }

    fun getAccessToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[accessToken]
        }
    }

    suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[accessToken] = token
        }
    }

    suspend fun deleteAccessToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(accessToken)
        }
    }
}