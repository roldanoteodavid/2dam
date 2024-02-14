package org.example.app.ui.screens.recursos;

import org.example.app.domain.modelo.Recurso;

import java.util.List;

public record RecursosState(List<String> usersList, List<Recurso> recursosList, String error, String mensaje, boolean isLoading, Recurso recurso) {
}
