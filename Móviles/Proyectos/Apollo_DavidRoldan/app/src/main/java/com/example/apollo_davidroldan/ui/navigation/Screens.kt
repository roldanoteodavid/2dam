package com.example.apollo_davidroldan.ui.navigation


val screensBottomBar = listOf(
    Screens("directores"),
    Screens("peliculas"),
    Screens("actores"),
    Screens("generos"),
    Screens("premios"),
)

data class Screens(val route: String) {

}
