package com.example.login_davidroldan.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.login_davidroldan.data.local.modelo.AutorEntity

@Dao
interface AutorDao {

    @Query("SELECT * from autor")
    suspend fun getAutores(): List<AutorEntity>

    @Delete
    suspend fun deleteAutor(autor: AutorEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAutor(autor: AutorEntity)

}