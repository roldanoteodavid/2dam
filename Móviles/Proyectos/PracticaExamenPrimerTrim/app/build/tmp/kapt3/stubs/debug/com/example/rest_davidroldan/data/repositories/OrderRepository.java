package com.example.rest_davidroldan.data.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/example/rest_davidroldan/data/repositories/OrderRepository;", "", "remoteDataSource", "Lcom/example/rest_davidroldan/data/sources/remote/RemoteDataSource;", "(Lcom/example/rest_davidroldan/data/sources/remote/RemoteDataSource;)V", "addOrder", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "", "order", "Lcom/example/rest_davidroldan/domain/modelo/Order;", "(Lcom/example/rest_davidroldan/domain/modelo/Order;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOrder", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrders", "", "app_debug"})
@dagger.hilt.android.scopes.ActivityRetainedScoped
public final class OrderRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.rest_davidroldan.data.sources.remote.RemoteDataSource remoteDataSource = null;
    
    @javax.inject.Inject
    public OrderRepository(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.data.sources.remote.RemoteDataSource remoteDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getOrders(int id, @org.jetbrains.annotations.NotNull
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