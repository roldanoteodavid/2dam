package com.example.rest_davidroldan.data.sources.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00150\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J%\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00150\b2\u0006\u0010\u0018\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/rest_davidroldan/data/sources/remote/RemoteDataSource;", "", "customerService", "Lcom/example/rest_davidroldan/data/sources/remote/CustomerService;", "orderService", "Lcom/example/rest_davidroldan/data/sources/remote/OrderService;", "(Lcom/example/rest_davidroldan/data/sources/remote/CustomerService;Lcom/example/rest_davidroldan/data/sources/remote/OrderService;)V", "addOrder", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "", "order", "Lcom/example/rest_davidroldan/domain/modelo/Order;", "(Lcom/example/rest_davidroldan/domain/modelo/Order;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomer", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOrder", "getCustomer", "Lcom/example/rest_davidroldan/domain/modelo/Customer;", "getCustomers", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrders", "customerid", "app_debug"})
public final class RemoteDataSource {
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.data.sources.remote.CustomerService customerService = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.data.sources.remote.OrderService orderService = null;
    
    @javax.inject.Inject
    public RemoteDataSource(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.data.sources.remote.CustomerService customerService, @org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.data.sources.remote.OrderService orderService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCustomers(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.rest_davidroldan.domain.modelo.Customer>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCustomer(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<com.example.rest_davidroldan.domain.modelo.Customer>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteCustomer(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getOrders(int customerid, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.rest_davidroldan.domain.modelo.Order>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addOrder(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.domain.modelo.Order order, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteOrder(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<kotlin.Unit>> $completion) {
        return null;
    }
}