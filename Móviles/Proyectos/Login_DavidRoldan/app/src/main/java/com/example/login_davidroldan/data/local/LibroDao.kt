package com.example.login_davidroldan.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.login_davidroldan.data.local.modelo.LibroEntity

@Dao
interface LibroDao {
    @Query("SELECT * from libro where authorId = :id")
    suspend fun getLibros(id: Int): List<LibroEntity>

    @Delete
    suspend fun deleteLibro(libro: LibroEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: LibroEntity)
}