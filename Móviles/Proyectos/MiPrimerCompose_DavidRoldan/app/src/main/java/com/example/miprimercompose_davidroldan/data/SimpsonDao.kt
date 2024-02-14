package com.example.miprimercompose_davidroldan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.miprimercompose_davidroldan.data.modelo.SimpsonEntity

@Dao
interface SimpsonDao {

    @Query("SELECT * from simpsons")
    suspend fun getSimpsons(): List<SimpsonEntity>

    @Delete
    suspend fun deleteSimpson(simpson: SimpsonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSimpson(simpson: SimpsonEntity): Long

    @Update
    suspend fun updateSimpson(simpson: SimpsonEntity)

    @Query("SELECT * FROM simpsons WHERE id > :id LIMIT 1")
    suspend fun getSiguiente(id: Int): SimpsonEntity?

    @Query("SELECT * FROM simpsons WHERE id < :id ORDER BY id DESC LIMIT 1")
    suspend fun getAnterior(id: Int): SimpsonEntity?
}