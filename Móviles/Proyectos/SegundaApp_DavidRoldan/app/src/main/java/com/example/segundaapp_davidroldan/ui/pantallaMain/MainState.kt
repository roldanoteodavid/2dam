package com.example.segundaapp_davidroldan.ui.pantallaMain

import com.example.segundaapp_davidroldan.domain.modelo.Simpson

data class MainState(
    val simpson: Simpson = Simpson(),
    val error: String? = null,
    val nosiguiente: Boolean? = true,
    val noanterior: Boolean? = true,
    val updatedelete: Boolean? = true
)