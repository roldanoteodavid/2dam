package com.example.examenmovprimertrim_davidroldan.framework.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010 \u001a\u00020\u0017H\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/framework/main/EquiposViewModel;", "Landroidx/lifecycle/ViewModel;", "getEquiposUseCase", "Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/GetEquiposUseCase;", "deleteEquipoUseCase", "Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/DeleteEquipoUseCase;", "(Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/GetEquiposUseCase;Lcom/example/examenmovprimertrim_davidroldan/domain/usecases/DeleteEquipoUseCase;)V", "_error", "Landroidx/lifecycle/MutableLiveData;", "", "_uiState", "Lcom/example/rest_davidroldan/framework/main/EquiposState;", "kotlin.jvm.PlatformType", "error", "Landroidx/lifecycle/LiveData;", "getError", "()Landroidx/lifecycle/LiveData;", "selectedEquipos", "", "Lcom/example/examen/domain/Equipo;", "uiState", "getUiState", "deleteEquipo", "", "equipo", "deleteEquipos", "getEquipos", "handleEvent", "event", "Lcom/example/rest_davidroldan/framework/main/EquiposEvent;", "isSelected", "", "resetSelectMode", "seleccionaEquipo", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class EquiposViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.domain.usecases.GetEquiposUseCase getEquiposUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.examenmovprimertrim_davidroldan.domain.usecases.DeleteEquipoUseCase deleteEquipoUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.examen.domain.Equipo> selectedEquipos;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.rest_davidroldan.framework.main.EquiposState> _uiState = null;
    
    @javax.inject.Inject
    public EquiposViewModel(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.domain.usecases.GetEquiposUseCase getEquiposUseCase, @org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.domain.usecases.DeleteEquipoUseCase deleteEquipoUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.rest_davidroldan.framework.main.EquiposState> getUiState() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.framework.main.EquiposEvent event) {
    }
    
    private final void resetSelectMode() {
    }
    
    private final void getEquipos() {
    }
    
    private final void deleteEquipos() {
    }
    
    private final void deleteEquipo(com.example.examen.domain.Equipo equipo) {
    }
    
    private final void seleccionaEquipo(com.example.examen.domain.Equipo equipo) {
    }
    
    private final boolean isSelected(com.example.examen.domain.Equipo equipo) {
        return false;
    }
}