package com.example.examenmovprimertrim_davidroldan.framework.newjugador;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent;", "", "()V", "AddJugador", "ErrorVisto", "Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent$AddJugador;", "Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent$ErrorVisto;", "app_debug"})
public abstract class NewJugadorEvent {
    
    private NewJugadorEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent$AddJugador;", "Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent;", "jugador", "Lcom/example/examen/domain/Jugador;", "(Lcom/example/examen/domain/Jugador;)V", "getJugador", "()Lcom/example/examen/domain/Jugador;", "app_debug"})
    public static final class AddJugador extends com.example.examenmovprimertrim_davidroldan.framework.newjugador.NewJugadorEvent {
        @org.jetbrains.annotations.NotNull
        private final com.example.examen.domain.Jugador jugador = null;
        
        public AddJugador(@org.jetbrains.annotations.NotNull
        com.example.examen.domain.Jugador jugador) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.examen.domain.Jugador getJugador() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent$ErrorVisto;", "Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent;", "()V", "app_debug"})
    public static final class ErrorVisto extends com.example.examenmovprimertrim_davidroldan.framework.newjugador.NewJugadorEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.examenmovprimertrim_davidroldan.framework.newjugador.NewJugadorEvent.ErrorVisto INSTANCE = null;
        
        private ErrorVisto() {
        }
    }
}