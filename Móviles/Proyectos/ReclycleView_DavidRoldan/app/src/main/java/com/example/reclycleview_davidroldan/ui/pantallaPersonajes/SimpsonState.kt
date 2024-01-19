package com.example.reclycleview_davidroldan.ui.pantallaPersonajes

import com.example.reclycleview_davidroldan.data.model.Simpsonjson

data class SimpsonState(
    val simpson: Simpsonjson = Simpsonjson(),
    val error: String? = null
)