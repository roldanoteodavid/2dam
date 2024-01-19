package com.example.examenmovprimertrim_davidroldan.data.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/data/repositories/JugadoresRepository;", "", "remoteDataSource", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/RemoteDataSource;", "(Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/RemoteDataSource;)V", "addJugador", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "", "jugador", "Lcom/example/examen/domain/Jugador;", "(Lcom/example/examen/domain/Jugador;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteJugador", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJugadoreresEquipo", "", "getJugadorers", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.scopes.ActivityRetainedScoped
public final class JugadoresRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.data.sources.remote.RemoteDataSource remoteDataSource = null;
    
    @javax.inject.Inject
    public JugadoresRepository(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.data.sources.remote.RemoteDataSource remoteDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getJugadorers(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.examen.domain.Jugador>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getJugadoreresEquipo(@org.jetbrains.annotations.NotNull
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
}