package com.example.login_davidroldan.framework.autores

import com.example.login_davidroldan.domain.modelo.Autor


data class AutorState(
    val autores: List<Autor> = emptyList(),
    val autoresSelected: List<Autor> = emptyList(),
    val selectMode: Boolean = false,
    val error: String? = null,
)