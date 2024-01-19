package com.example.reclycleview_davidroldan.ui.pantallaMain

import com.example.reclycleview_davidroldan.data.model.Simpsonjson

data class MainState(
    val error: String? = null,
    val lista: List<Simpsonjson> = emptyList()
)
