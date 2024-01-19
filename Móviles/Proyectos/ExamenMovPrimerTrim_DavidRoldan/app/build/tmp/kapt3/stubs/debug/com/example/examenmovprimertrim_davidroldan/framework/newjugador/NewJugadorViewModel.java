package com.example.examenmovprimertrim_davidroldan.framework.newjugador;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0014"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorViewModel;", "Landroidx/lifecycle/ViewModel;", "addJugadorUseCase", "Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/AddJugadorUseCase;", "(Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/AddJugadorUseCase;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorState;", "kotlin.jvm.PlatformType", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "addJugador", "", "jugador", "Lcom/example/examen/domain/Jugador;", "handleEvent", "event", "Lcom/example/examenmovprimertrim_davidroldan/framework/newjugador/NewJugadorEvent;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class NewJugadorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.domain.usecases.AddJugadorUseCase addJugadorUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.examenmovprimertrim_davidroldan.framework.newjugador.NewJugadorState> _uiState = null;
    
    @javax.inject.Inject
    public NewJugadorViewModel(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.domain.usecases.AddJugadorUseCase addJugadorUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.examenmovprimertrim_davidroldan.framework.newjugador.NewJugadorState> getUiState() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.framework.newjugador.NewJugadorEvent event) {
    }
    
    private final void addJugador(com.example.examen.domain.Jugador jugador) {
    }
}