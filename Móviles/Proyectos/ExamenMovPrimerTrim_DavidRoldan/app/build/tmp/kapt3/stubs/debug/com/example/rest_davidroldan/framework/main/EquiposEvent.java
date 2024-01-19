package com.example.rest_davidroldan.framework.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "", "()V", "DeleteEquipo", "DeleteEquipoosSeleccionados", "ErrorVisto", "GetEquipos", "ResetSelectMode", "SeleccionaEquipos", "StartSelectMode", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$DeleteEquipo;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$DeleteEquipoosSeleccionados;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$GetEquipos;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$ResetSelectMode;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$SeleccionaEquipos;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent$StartSelectMode;", "app_debug"})
public abstract class EquiposEvent {
    
    private EquiposEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$DeleteEquipo;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "equipo", "Lcom/example/examen/domain/Equipo;", "(Lcom/example/examen/domain/Equipo;)V", "getEquipo", "()Lcom/example/examen/domain/Equipo;", "app_debug"})
    public static final class DeleteEquipo extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        @org.jetbrains.annotations.NotNull
        private final com.example.examen.domain.Equipo equipo = null;
        
        public DeleteEquipo(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo equipo) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.examen.domain.Equipo getEquipo() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$DeleteEquipoosSeleccionados;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "()V", "app_debug"})
    public static final class DeleteEquipoosSeleccionados extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        
        public DeleteEquipoosSeleccionados() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "()V", "app_debug"})
    public static final class ErrorVisto extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.EquiposEvent.ErrorVisto INSTANCE = null;
        
        private ErrorVisto() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$GetEquipos;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "()V", "app_debug"})
    public static final class GetEquipos extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.EquiposEvent.GetEquipos INSTANCE = null;
        
        private GetEquipos() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$ResetSelectMode;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "()V", "app_debug"})
    public static final class ResetSelectMode extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.EquiposEvent.ResetSelectMode INSTANCE = null;
        
        private ResetSelectMode() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$SeleccionaEquipos;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "equipo", "Lcom/example/examen/domain/Equipo;", "(Lcom/example/examen/domain/Equipo;)V", "getEquipo", "()Lcom/example/examen/domain/Equipo;", "app_debug"})
    public static final class SeleccionaEquipos extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        @org.jetbrains.annotations.NotNull
        private final com.example.examen.domain.Equipo equipo = null;
        
        public SeleccionaEquipos(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Equipo equipo) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.examen.domain.Equipo getEquipo() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/EquiposEvent$StartSelectMode;", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "()V", "app_debug"})
    public static final class StartSelectMode extends com.example.rest_davidroldan.framework.main.EquiposEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.EquiposEvent.StartSelectMode INSTANCE = null;
        
        private StartSelectMode() {
        }
    }
}