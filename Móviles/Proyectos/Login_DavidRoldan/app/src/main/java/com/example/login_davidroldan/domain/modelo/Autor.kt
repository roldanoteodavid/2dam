package com.example.login_davidroldan.domain.modelo

data class Autor (
    val authorId: Int,
    val name: String,
    val birthDate: String,
    var isSelected: Boolean = false,
)