package com.example.reclycleview_davidroldan.domain.modelo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class Simpson (
    val id: Int = 0,
    val nombre: String = "",
    val age: Int = 0,
    val vivo: Boolean = false,
    val gender: String = "",
    val occupation: String = "",
    val birthdate: LocalDate = LocalDate.now()
):Parcelable