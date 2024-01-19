package com.example.examenmovprimertrim_davidroldan.data.sources.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J%\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/RemoteDataSource;", "", "equiposService", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/EquiposService;", "jugadoresService", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/JugadoresService;", "(Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/EquiposService;Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/JugadoresService;)V", "addJugador", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "", "jugador", "Lcom/example/examen/domain/Jugador;", "(Lcom/example/examen/domain/Jugador;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteEquipo", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteJugador", "getEquipos", "", "Lcom/example/examen/domain/Equipo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJugadores", "getJugadoresEquipo", "app_debug"})
public final class RemoteDataSource {
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.data.sources.remote.EquiposService equiposService = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.data.sources.remote.JugadoresService jugadoresService = null;
    
    @javax.inject.Inject
    public RemoteDataSource(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.data.sources.remote.EquiposService equiposService, @org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.data.sources.remote.JugadoresService jugadoresService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getEquipos(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.examen.domain.Equipo>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getJugadores(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.examen.domain.Jugador>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getJugadoresEquipo(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.examen.domain.Jugador>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addJugador(@org.jetbrains.annotations.NotNull
    com.example.examen.domain.Jugador jugador, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteJugador(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteEquipo(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
}