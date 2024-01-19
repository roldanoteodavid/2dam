package com.example.rest_davidroldan.framework.neworder;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0014"}, d2 = {"Lcom/example/rest_davidroldan/framework/neworder/NewOrderViewModel;", "Landroidx/lifecycle/ViewModel;", "addOrderUseCase", "Lcom/example/rest_davidroldan/domain/usecases/AddOrderUseCase;", "(Lcom/example/rest_davidroldan/domain/usecases/AddOrderUseCase;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/rest_davidroldan/framework/neworder/NewOrderState;", "kotlin.jvm.PlatformType", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "addOrder", "", "order", "Lcom/example/rest_davidroldan/domain/modelo/Order;", "handleEvent", "event", "Lcom/example/rest_davidroldan/framework/neworder/NewOrderEvent;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class NewOrderViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.domain.usecases.AddOrderUseCase addOrderUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.rest_davidroldan.framework.neworder.NewOrderState> _uiState = null;
    
    @javax.inject.Inject
    public NewOrderViewModel(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.domain.usecases.AddOrderUseCase addOrderUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.rest_davidroldan.framework.neworder.NewOrderState> getUiState() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.framework.neworder.NewOrderEvent event) {
    }
    
    private final void addOrder(com.example.rest_davidroldan.domain.modelo.Order order) {
    }
}