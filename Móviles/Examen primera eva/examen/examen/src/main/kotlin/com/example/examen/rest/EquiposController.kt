package com.example.examen.rest

import com.example.examen.data.RepositoryJugadores
import com.example.examen.data.RepositoryEquipos
import com.example.examen.domain.Equipo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/equipos")
class EquiposController (val repositoryPacientes: RepositoryEquipos, val repositoryJugadores: RepositoryJugadores) {

    @GetMapping("")
    fun getEquipos() = repositoryPacientes.getEquipos()


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEquipo(@PathVariable id: String) {
        repositoryJugadores.deleteJugadoresDeEquipo(UUID.fromString(id))
        repositoryPacientes.deleteEquipo(UUID.fromString(id))
    }

    @DeleteMapping("/multiborrar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEquipo(@RequestParam ids: List<String>) {
        repositoryJugadores.deleteJugadoresDeMultiEquipo(ids)
        repositoryPacientes.deleteMultiEquipo(ids)
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun addEquipo(@RequestBody equipo: Equipo) {
        repositoryPacientes.addEquipo(equipo)
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateEquipo(@RequestBody equipo: Equipo) {
        repositoryPacientes.updateEquipo(equipo)
    }


}







