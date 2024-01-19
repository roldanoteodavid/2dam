package com.example.segundaapp_davidroldan.domain.modelo

data class Simpson(
    val nombre: String = "",
    val edad: Int = 0,
    val vivo: Boolean = false,
    val genero: String = "",
    val profesion: String = ""
) {
    override fun toString(): String {
        return "Nombre: " + nombre +
                "\nEdad: " + edad +
                "\nVivo: " + vivo +
                "\nGenero: " + genero +
                "\nProfesión: " + profesion
    }
}