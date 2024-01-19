package com.example.rest_davidroldan.framework.orders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0017"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/JugadoresViewModel;", "Landroidx/lifecycle/ViewModel;", "getJugadoresUseCase", "Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/GetJugadoresUseCase;", "deleteJugadorUseCase", "Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/DeleteJugadorUseCase;", "(Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/GetJugadoresUseCase;Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/DeleteJugadorUseCase;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/rest_davidroldan/framework/orders/JugadoresState;", "kotlin.jvm.PlatformType", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "deleteJugador", "", "id", "Ljava/util/UUID;", "getJugadores", "handleEvent", "event", "Lcom/example/rest_davidroldan/framework/orders/JugadoresEvent;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class JugadoresViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.domain.usecases.GetJugadoresUseCase getJugadoresUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.domain.usecases.DeleteJugadorUseCase deleteJugadorUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.rest_davidroldan.framework.orders.JugadoresState> _uiState = null;
    
    @javax.inject.Inject
    public JugadoresViewModel(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.domain.usecases.GetJugadoresUseCase getJugadoresUseCase, @org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.domain.usecases.DeleteJugadorUseCase deleteJugadorUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.rest_davidroldan.framework.orders.JugadoresState> getUiState() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.framework.orders.JugadoresEvent event) {
    }
    
    private final void getJugadores() {
    }
    
    private final void deleteJugador(java.util.UUID id) {
    }
}