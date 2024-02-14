package com.example.miprimercompose_davidroldan.framework.main

sealed class MainEvent {
    class DeleteSimpson() : MainEvent()
    class AddSimpson() : MainEvent()
    class GetSiguienteSimpson() : MainEvent()
    class GetAnteriorSimpson() : MainEvent()
    class UpdateSimpson() : MainEvent()

    class OnNameChange(val name: String) : MainEvent()
    class OnEdadChange(val edad: Float) : MainEvent()
    class OnVivoChange(val vivo: Boolean) : MainEvent()
    class OnGeneroChange(val genero: String) : MainEvent()
    class OnProfesionChange(val profesion: String) : MainEvent()

    object ErrorVisto : MainEvent()
}