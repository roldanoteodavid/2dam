package com.example.miprimercompose_davidroldan.framework.main

import com.example.miprimercompose_davidroldan.domain.modelo.Simpson

data class MainState(
    val error: String? = null,
    val simpson: Simpson = Simpson(),
    val nosiguiente: Boolean? = true,
    val noanterior: Boolean? = false,
    val updatedelete: Boolean? = true
)
