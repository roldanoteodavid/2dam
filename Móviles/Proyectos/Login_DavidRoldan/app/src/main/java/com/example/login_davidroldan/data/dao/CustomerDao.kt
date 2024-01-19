package com.example.login_davidroldan.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.login_davidroldan.data.model.CustomerEntity

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers")
    fun getAll(): List<CustomerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(customers: List<CustomerEntity>)

    @Delete
    fun delete(customer: CustomerEntity)

    @Delete
    fun deleteAll(customer: List<CustomerEntity>)
}