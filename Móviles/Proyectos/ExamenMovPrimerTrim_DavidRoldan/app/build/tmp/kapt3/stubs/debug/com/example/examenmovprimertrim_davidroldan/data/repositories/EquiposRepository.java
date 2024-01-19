package com.example.examenmovprimertrim_davidroldan.data.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/data/repositories/EquiposRepository;", "", "remoteDataSource", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/RemoteDataSource;", "(Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/RemoteDataSource;)V", "deleteEquipo", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEquipos", "", "Lcom/example/examen/domain/Equipo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.scopes.ActivityRetainedScoped
public final class EquiposRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.data.sources.remote.RemoteDataSource remoteDataSource = null;
    
    @javax.inject.Inject
    public EquiposRepository(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.data.sources.remote.RemoteDataSource remoteDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getEquipos(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.examen.domain.Equipo>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteEquipo(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
}