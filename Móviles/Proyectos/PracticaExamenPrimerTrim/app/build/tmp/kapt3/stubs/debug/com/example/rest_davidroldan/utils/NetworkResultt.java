package com.example.rest_davidroldan.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0014\u0015B\u001f\b\u0004\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J7\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0000\"\u0004\b\u0001\u0010\u000f2#\u0010\u0010\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00018\u0000\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u0002H\u000f0\u0011R\u001e\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0001\u0002\u0016\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/example/rest_davidroldan/utils/NetworkResultt;", "T", "", "data", "message", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getMessage", "()Ljava/lang/String;", "map", "R", "transform", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Error", "Success", "Lcom/example/rest_davidroldan/utils/NetworkResultt$Error;", "Lcom/example/rest_davidroldan/utils/NetworkResultt$Success;", "app_debug"})
public abstract class NetworkResultt<T extends java.lang.Object> {
    @org.jetbrains.annotations.Nullable
    private T data;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String message = null;
    
    private NetworkResultt(T data, java.lang.String message) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final T getData() {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.Nullable
    T p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final <R extends java.lang.Object>com.example.rest_davidroldan.utils.NetworkResultt<R> map(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super T, ? extends R> transform) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0001\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/utils/NetworkResultt$Error;", "T", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "message", "", "data", "(Ljava/lang/String;Ljava/lang/Object;)V", "app_debug"})
    public static final class Error<T extends java.lang.Object> extends com.example.rest_davidroldan.utils.NetworkResultt<T> {
        
        public Error(@org.jetbrains.annotations.NotNull
        java.lang.String message, @org.jetbrains.annotations.Nullable
        T data) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/rest_davidroldan/utils/NetworkResultt$Success;", "T", "Lcom/example/rest_davidroldan/utils/NetworkResultt;", "data", "(Ljava/lang/Object;)V", "app_debug"})
    public static final class Success<T extends java.lang.Object> extends com.example.rest_davidroldan.utils.NetworkResultt<T> {
        
        public Success(T data) {
        }
    }
}