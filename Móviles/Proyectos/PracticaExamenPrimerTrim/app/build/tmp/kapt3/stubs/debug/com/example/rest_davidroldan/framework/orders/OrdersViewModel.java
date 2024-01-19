package com.example.rest_davidroldan.framework.orders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/OrdersViewModel;", "Landroidx/lifecycle/ViewModel;", "getCustomerIdUseCase", "Lcom/example/rest_davidroldan/domain/usecases/GetCustomerIdUseCase;", "getOrdersUseCase", "Lcom/example/rest_davidroldan/domain/usecases/GetOrdersUseCase;", "deleteOrderUseCase", "Lcom/example/rest_davidroldan/domain/usecases/DeleteOrderUseCase;", "(Lcom/example/rest_davidroldan/domain/usecases/GetCustomerIdUseCase;Lcom/example/rest_davidroldan/domain/usecases/GetOrdersUseCase;Lcom/example/rest_davidroldan/domain/usecases/DeleteOrderUseCase;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/rest_davidroldan/framework/orders/OrdersState;", "kotlin.jvm.PlatformType", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "deleteOrder", "", "id", "", "getCustomerId", "getOrders", "handleEvent", "event", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class OrdersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.domain.usecases.GetCustomerIdUseCase getCustomerIdUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.domain.usecases.GetOrdersUseCase getOrdersUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.domain.usecases.DeleteOrderUseCase deleteOrderUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.rest_davidroldan.framework.orders.OrdersState> _uiState = null;
    
    @javax.inject.Inject
    public OrdersViewModel(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.domain.usecases.GetCustomerIdUseCase getCustomerIdUseCase, @org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.domain.usecases.GetOrdersUseCase getOrdersUseCase, @org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.domain.usecases.DeleteOrderUseCase deleteOrderUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.rest_davidroldan.framework.orders.OrdersState> getUiState() {
        return null;
    }
    
    public final void handleEvent(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.framework.orders.OrdersEvent event) {
    }
    
    private final void getOrders() {
    }
    
    private final void getOrders(int id) {
    }
    
    private final void deleteOrder(int id) {
    }
    
    private final void getCustomerId(int id) {
    }
}