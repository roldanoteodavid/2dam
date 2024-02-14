package com.example.miprimercompose_davidroldan.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miprimercompose_davidroldan.data.modelo.SimpsonEntity

@Database(entities = [SimpsonEntity::class], version = 1)
abstract class SimpsonsRoomDatabase : RoomDatabase() {
    abstract fun simpsonDao(): SimpsonDao
}