package com.example.rest_davidroldan.data.sources.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\n0\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/example/rest_davidroldan/data/sources/remote/CustomerService;", "", "deleteCustomer", "Lretrofit2/Response;", "Lcom/example/rest_davidroldan/data/model/CustomerResponse;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCustomer", "getCustomers", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface CustomerService {
    
    @retrofit2.http.GET(value = "api/customers")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCustomers(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.rest_davidroldan.data.model.CustomerResponse>>> $completion);
    
    @retrofit2.http.GET(value = "api/customers/{id}")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCustomer(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.rest_davidroldan.data.model.CustomerResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "api/customers/{id}")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteCustomer(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.rest_davidroldan.data.model.CustomerResponse>> $completion);
}