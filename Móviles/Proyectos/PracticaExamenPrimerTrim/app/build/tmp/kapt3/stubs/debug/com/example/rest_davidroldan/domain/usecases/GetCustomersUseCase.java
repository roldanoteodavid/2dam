package com.example.rest_davidroldan.domain.usecases;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\u0086B\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/example/rest_davidroldan/domain/usecases/GetCustomersUseCase;", "", "repository", "Lcom/example/rest_davidroldan/data/repositories/CustomerRepository;", "(Lcom/example/rest_davidroldan/data/repositories/CustomerRepository;)V", "getRepository", "()Lcom/example/rest_davidroldan/data/repositories/CustomerRepository;", "setRepository", "invoke", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "", "Lcom/example/rest_davidroldan/domain/modelo/Customer;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GetCustomersUseCase {
    @org.jetbrains.annotations.NotNull
    private com.example.rest_davidroldan.data.repositories.CustomerRepository repository;
    
    @javax.inject.Inject
    public GetCustomersUseCase(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.data.repositories.CustomerRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.rest_davidroldan.data.repositories.CustomerRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull
    com.example.rest_davidroldan.data.repositories.CustomerRepository p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.rest_davidroldan.utils.NetworkResultt<java.util.List<com.example.rest_davidroldan.domain.modelo.Customer>>> $completion) {
        return null;
    }
}