package com.example.examen.rest

import com.example.examen.data.RepositoryJugadores
import com.example.examen.domain.Jugador
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/jugadores")
class JugadoresController(val repositoryJugadores: RepositoryJugadores) {


    @GetMapping("")
    fun getJugadores() = repositoryJugadores.getJugadores()

    @GetMapping("/{idEquipo}")
    fun getJugadoresEquipo(@PathVariable idEquipo: String) = repositoryJugadores.getJugadoresDeEquipo(UUID.fromString(idEquipo))


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteJugador(@PathVariable id: String) {
        repositoryJugadores.deleteJugador(UUID.fromString(id))
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun addJugador(@RequestBody jugador: Jugador) {
        repositoryJugadores.addJugador(jugador)
    }
}







