package com.example.examenmovprimertrim_davidroldan.data.sources.remote.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\b\u0010\u0012\u001a\u00020\fH\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/di/NetworkModule;", "", "()V", "provideConverterMoshiFactory", "Lretrofit2/converter/moshi/MoshiConverterFactory;", "provideCurrencyService", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/EquiposService;", "retrofit", "Lretrofit2/Retrofit;", "provideHttpClient", "Lokhttp3/OkHttpClient;", "serviceInterceptor", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/ServiceInterceptor;", "provideOrderService", "Lcom/example/examenmovprimertrim_davidroldan/data/sources/remote/JugadoresService;", "provideRetrofit", "okHttpClient", "moshiConverterFactory", "provideServiceInterceptor", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull
    public static final com.example.examenmovprimertrim_davidroldan.data.sources.remote.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.example.examenmovprimertrim_davidroldan.data.sources.remote.ServiceInterceptor provideServiceInterceptor() {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final okhttp3.OkHttpClient provideHttpClient(@org.jetbrains.annotations.NotNull
    com.example.examenmovprimertrim_davidroldan.data.sources.remote.ServiceInterceptor serviceInterceptor) {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final retrofit2.converter.moshi.MoshiConverterFactory provideConverterMoshiFactory() {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Retrofit provideRetrofit(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okHttpClient, @org.jetbrains.annotations.NotNull
    retrofit2.converter.moshi.MoshiConverterFactory moshiConverterFactory) {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.example.examenmovprimertrim_davidroldan.data.sources.remote.EquiposService provideCurrencyService(@org.jetbrains.annotations.NotNull
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.example.examenmovprimertrim_davidroldan.data.sources.remote.JugadoresService provideOrderService(@org.jetbrains.annotations.NotNull
    retrofit2.Retrofit retrofit) {
        return null;
    }
}