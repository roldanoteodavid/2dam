package com.example.login_davidroldan.data.model

import com.example.login_davidroldan.domain.modelo.Customer
import com.example.login_davidroldan.utils.Constants.DOB
import com.example.login_davidroldan.utils.Constants.EMAIL
import com.example.login_davidroldan.utils.Constants.FIRSTNAME
import com.example.login_davidroldan.utils.Constants.ID
import com.example.login_davidroldan.utils.Constants.LASTNAME
import com.example.login_davidroldan.utils.Constants.PHONE
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class CustomerResponse (
    @SerializedName(ID)
    val id: Int,
    @SerializedName(FIRSTNAME)
    val firstname: String,
    @SerializedName(LASTNAME)
    val lastname: String,
    @SerializedName(EMAIL)
    val email: String?,
    @SerializedName(PHONE)
    val phone: String?,
    @SerializedName(DOB)
    val dob: LocalDate?
)
fun CustomerResponse.toCustomer() : Customer = Customer(id, firstname, lastname, email, phone, dob)
