package com.example.rest_davidroldan.framework.orders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent;", "", "()V", "DeleteJugador", "ErrorVisto", "GetJugadores", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent$DeleteJugador;", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent$GetJugadores;", "app_debug"})
public abstract class JugadoresEvent {
    
    private JugadoresEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent$DeleteJugador;", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent;", "id", "Ljava/util/UUID;", "(Ljava/util/UUID;)V", "getId", "()Ljava/util/UUID;", "app_debug"})
    public static final class DeleteJugador extends com.example.rest_davidroldan.framework.orders.JugadoresEvent {
        @org.jetbrains.annotations.NotNull
        private final java.util.UUID id = null;
        
        public DeleteJugador(@org.jetbrains.annotations.NotNull
        java.util.UUID id) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.UUID getId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent;", "()V", "app_debug"})
    public static final class ErrorVisto extends com.example.rest_davidroldan.framework.orders.JugadoresEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.orders.JugadoresEvent.ErrorVisto INSTANCE = null;
        
        private ErrorVisto() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent$GetJugadores;", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent;", "()V", "app_debug"})
    public static final class GetJugadores extends com.example.rest_davidroldan.framework.orders.JugadoresEvent {
        
        public GetJugadores() {
        }
    }
}