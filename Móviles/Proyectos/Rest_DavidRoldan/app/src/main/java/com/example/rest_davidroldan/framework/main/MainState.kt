package com.example.rest_davidroldan.framework.main


data class MainState(val personas: List<Persona> = emptyList(),
                     val error: String? = null,)