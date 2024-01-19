package com.example.recyclerview_davidroldan.data

import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import java.lang.reflect.Type
import java.time.LocalDate

class SimpsonRepositoty(file: InputStream? = null) {

    class LocalDateAdapter {
        @ToJson
        fun toJson(value: LocalDate): String {
            return value.toString()
        }

        @FromJson
        fun fromJson(value: String): LocalDate {
            return LocalDate.parse(value)
        }
    }


    companion object {
        private val lista = mutableListOf<Simpsonjson>()

    }

    init {
        if (lista.isEmpty()) {
            val moshi = Moshi.Builder()
                .add(LocalDateAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val listOfCardsType: Type = Types.newParameterizedType(
                MutableList::class.java,
                Simpsonjson::class.java
            )
            val personajes = file?.bufferedReader()?.readText()?.let {
                moshi.adapter<List<Simpsonjson>>(listOfCardsType)
                    .fromJson(it)
            }

            personajes?.let { lista.addAll(it) }
        }
    }

    fun getLista(): List<Simpsonjson> {
        return lista
    }


    fun getPersonaje(id: Int): Simpsonjson {
        return lista.stream().filter { it.id == id }.findFirst().get()
    }

    fun addSimpson(simpsonjson: Simpsonjson): Boolean {
        simpsonjson.id = getAutoId()
        return lista.add(simpsonjson)
    }

    private fun getAutoId() : Int{
        val maxId = lista.maxByOrNull { it.id }?.id ?: 0

        val nuevoId = maxId + 1

        return nuevoId

    }

    fun deleteSimpson(simpsonjson: Simpsonjson): Boolean {
        return lista.remove(simpsonjson)
    }


    fun updateSimpson(simpson: Simpsonjson, newsimpson: Simpsonjson) {
        newsimpson.id = simpson.id
        lista[lista.indexOf(simpson)] = newsimpson
    }
}