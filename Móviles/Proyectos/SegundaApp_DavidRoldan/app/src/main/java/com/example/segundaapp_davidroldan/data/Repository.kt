package com.example.segundaapp_davidroldan.data

import com.example.segundaapp_davidroldan.domain.modelo.Simpson


object Repository {
    private val simpsons = mutableListOf<Simpson>()

    init {
        simpsons.add(Simpson("Homer", 34, true, "Hombre", "Seguridad Nuclear"))
        simpsons.add(Simpson("Apu", 37, true, "Hombre", "Dependiente"))
        simpsons.add(Simpson("Moe", 62, true, "Hombre", "Tabernero"))
        simpsons.add(Simpson("Maude Flanders", 34, false, "Mujer", "Desconocida"))
        simpsons.add(Simpson("Ralph", 8, true, "Hombre", "Estudiante"))
    }

    private val mapPersonas = mutableMapOf<String, Simpson>()

    fun addSimpson(simpson: Simpson): Boolean =
        simpsons.add(simpson)


    fun getSimpsons(): List<Simpson> {
        return simpsons
    }

    fun getSimpson(id: Int): Simpson {
        if (simpsons.isEmpty()) {
            return Simpson()
        }
        if (id <= simpsons.size - 1 && id >= 0) {
            return simpsons[id]
        }
        return Simpson()
    }

    fun deleteSimpson(int: Int): Boolean {
        return simpsons.remove(simpsons[int])
    }


    fun updateSimpson(simpson: Simpson, newsimpson: Simpson) {
        simpsons[simpsons.indexOf(simpson)] = newsimpson
    }
}