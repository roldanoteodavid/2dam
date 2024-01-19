package com.example.login_davidroldan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.domain.modelo.Customer
import java.time.LocalDate

@Entity(tableName = Constantes.CUSTOMERS)
data class CustomerEntity(
    @PrimaryKey
    val id: Int = 0,
    val firstname: String,
    val lastname: String,
    val email: String?,
    val phone: String?,
    val dob: LocalDate?,
    var isSelected: Boolean = false
)

fun Customer.toCustomerEntity(): CustomerEntity =
    CustomerEntity(id, firstname, lastname, email, phone, dob, isSelected)

fun CustomerEntity.toCustomer(): Customer =
    Customer(id, firstname, lastname, email, phone, dob, isSelected)


