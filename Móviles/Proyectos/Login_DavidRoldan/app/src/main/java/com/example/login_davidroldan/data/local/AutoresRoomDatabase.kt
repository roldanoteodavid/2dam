package com.example.login_davidroldan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.login_davidroldan.data.dao.CustomerDao
import com.example.login_davidroldan.data.local.modelo.AutorEntity
import com.example.login_davidroldan.data.local.modelo.LibroEntity
import com.example.login_davidroldan.data.model.CustomerEntity

@Database(entities = [AutorEntity::class, LibroEntity::class, CustomerEntity::class], version = 2)
@TypeConverters(LocalDateConverter::class)
abstract class AutoresRoomDatabase : RoomDatabase() {
    abstract fun autorDao(): AutorDao
    abstract fun libroDao(): LibroDao
    abstract fun customerDao(): CustomerDao
}