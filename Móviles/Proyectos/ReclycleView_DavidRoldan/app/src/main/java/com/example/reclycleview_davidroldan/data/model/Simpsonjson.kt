package com.example.reclycleview_davidroldan.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@JsonClass(generateAdapter = true)
@Parcelize
data class Simpsonjson (
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "age")
    val age: Int = 0,
    @Json(name = "vivo")
    val vivo: Boolean = false,
    @Json(name = "gender")
    val gender: String = "",
    @Json(name = "occupation")
    val occupation: String = "",
    @Json(name = "birthdate")
    val birthdate: LocalDate = LocalDate.now(),
) : Parcelable