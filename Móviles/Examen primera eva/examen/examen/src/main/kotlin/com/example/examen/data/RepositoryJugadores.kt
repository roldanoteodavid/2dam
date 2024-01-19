package com.example.examen.data

import com.example.examen.domain.Jugador
import com.example.examen.errores.NotFoundException
import org.springframework.stereotype.Repository
import org.springframework.web.context.annotation.ApplicationScope
import java.time.LocalDate
import java.util.*

@Repository
@ApplicationScope
class RepositoryJugadores(final val repositoryEquipos: RepositoryEquipos) {

    private var _jugadors = mutableListOf<Jugador>()


    init {
        _jugadors.add(
            Jugador(
                nombre = "jose",
                posicion = "delantero",
                idEquipo = repositoryEquipos.getEquipos()[0].id,
                fechaContratacion = LocalDate.now(),
                actuacionesPartidos = mutableListOf("A", "B", "C"),
                equiposAnteriores = mutableListOf("D", "E", "F")
            )
            )
        _jugadors.add(
            Jugador(
                nombre = "pedro", posicion = "defensa",
                idEquipo = repositoryEquipos.getEquipos()[0].id,
                fechaContratacion = LocalDate.now(),
                actuacionesPartidos = mutableListOf("A", "B", "C"),
                equiposAnteriores = mutableListOf("D", "E", "F")
            )
        )
        _jugadors.add(
            Jugador(
                nombre = "sanote",
                posicion = "mediocentro",
                idEquipo = repositoryEquipos.getEquipos()[0].id,
                fechaContratacion = LocalDate.now(),
            )
        )
        _jugadors.add(
            Jugador(
                nombre = "juan jose",
                posicion = "portero",
                idEquipo = repositoryEquipos.getEquipos()[1].id,
                fechaContratacion = LocalDate.now(),
                actuacionesPartidos = mutableListOf("A", "B", "C"),
                equiposAnteriores = mutableListOf("D", "E", "F")
            )
        )
        _jugadors.add(
            Jugador(
                nombre = "jose luis ",
                posicion = "extremoizquierdo",
                idEquipo = repositoryEquipos.getEquipos()[1].id,
                fechaContratacion = LocalDate.now(),
                actuacionesPartidos = mutableListOf("A", "B", "C"),
                equiposAnteriores = mutableListOf("D", "E", "F")
            )
        )
        _jugadors.add(
            Jugador(
                nombre = "juan",
                posicion = "delantero pichici",
                idEquipo = repositoryEquipos.getEquipos()[2].id,
                fechaContratacion = LocalDate.now(),
                actuacionesPartidos = mutableListOf("A", "B", "C"),
                equiposAnteriores = mutableListOf("D", "E", "F")
            )
        )
        _jugadors.add(
            Jugador(
                nombre = "jose juan",
                posicion = "defensa",
                idEquipo = repositoryEquipos.getEquipos()[3].id,
                fechaContratacion = LocalDate.now(),
                actuacionesPartidos = mutableListOf("A", "B", "C"),
                equiposAnteriores = mutableListOf("D", "E", "F")
            )
        )
        _jugadors.add(
            Jugador(
                nombre = "pedro J",
                posicion = "defensa",
                idEquipo = repositoryEquipos.getEquipos()[3].id,
                fechaContratacion = LocalDate.now(),

            )
        )



    }


    fun getJugadores(): List<Jugador> {

        return _jugadors.toList()
    }

    fun deleteJugadoresDeEquipo(id : UUID) {
        val jugadoresDeEquipo = _jugadors.filter { it.idEquipo == id }.toList()
        _jugadors.removeAll(jugadoresDeEquipo)


    }
    fun deleteJugadoresDeMultiEquipo(ids : List<String>) {
        val listaUUIDS = ids.map { UUID.fromString(it) }
        _jugadors.removeIf{ it.idEquipo in listaUUIDS }
    }

    fun deleteJugador(id : UUID) {
        val jugador = _jugadors.find { it.id == id }
        jugador.let { _jugadors.remove(it) }


        if (jugador == null) throw NotFoundException("jugador no encontrado")
    }

    fun getJugadoresDeEquipo(idEquipo: UUID) : List<Jugador> {
        val listado = _jugadors.filter { it.idEquipo == idEquipo }.toList()

        if(listado.isEmpty()) throw NotFoundException("jugadores no encontrados")

        return listado
    }
    fun addJugador(jugador: Jugador) {

        repositoryEquipos.getEquipos().find { it.id == jugador.idEquipo }?.let {
            _jugadors.add(jugador)
        } ?: throw NotFoundException("equipo no encontrado")

    }


}
