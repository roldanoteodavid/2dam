package com.example.examen.data

import com.example.examen.domain.Equipo
import org.springframework.stereotype.Repository
import org.springframework.web.context.annotation.ApplicationScope
import java.util.*


@Repository
@ApplicationScope
class RepositoryEquipos() {

    private val _equipos = mutableListOf<Equipo>()

    init {
        _equipos.add(Equipo(nombre = "Betis"))
        _equipos.add(Equipo(nombre = "Sevilla"))
        _equipos.add(Equipo(nombre = "Alaves"))
        _equipos.add(Equipo(nombre = "Cadiz"))
        _equipos.add(Equipo(nombre = "Osasuna"))
        _equipos.add(Equipo(nombre = "Valladolid"))
        _equipos.add(Equipo(nombre = "Rayo Vallecano"))
    }

    fun getEquipos() = _equipos.toList()

    fun deleteEquipo(id: UUID) {
        _equipos.removeIf { it.id == id }
    }

    fun deleteMultiEquipo(ids: List<String>) {
        val listaUUID = ids.map { UUID.fromString(it) }
        _equipos.removeIf { it.id in listaUUID }
    }

    fun addEquipo(equipo: Equipo) {
        _equipos.add(equipo)
    }

    fun updateEquipo(equipo: Equipo) {
        val equipoToUpdate = _equipos.find { it.id == equipo.id }
        equipoToUpdate?.nombre = equipo.nombre
    }


}


