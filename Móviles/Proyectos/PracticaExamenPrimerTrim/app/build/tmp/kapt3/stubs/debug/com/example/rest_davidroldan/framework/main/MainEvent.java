package com.example.rest_davidroldan.framework.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\u0003\u0004\u0005\u0006\u0007\b\t\nB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\b\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent;", "", "()V", "DeleteCustomer", "DeleteCustomersSeleccionadas", "ErrorVisto", "GetCustomers", "GetCustomersFiltradas", "ResetSelectMode", "SeleccionaCustomers", "StartSelectMode", "Lcom/example/rest_davidroldan/framework/main/MainEvent$DeleteCustomer;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$DeleteCustomersSeleccionadas;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$GetCustomers;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$GetCustomersFiltradas;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$ResetSelectMode;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$SeleccionaCustomers;", "Lcom/example/rest_davidroldan/framework/main/MainEvent$StartSelectMode;", "app_debug"})
public abstract class MainEvent {
    
    private MainEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$DeleteCustomer;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "customer", "Lcom/example/rest_davidroldan/domain/modelo/Customer;", "(Lcom/example/rest_davidroldan/domain/modelo/Customer;)V", "getCustomer", "()Lcom/example/rest_davidroldan/domain/modelo/Customer;", "app_debug"})
    public static final class DeleteCustomer extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        private final com.example.rest_davidroldan.domain.modelo.Customer customer = null;
        
        public DeleteCustomer(@org.jetbrains.annotations.NotNull
        com.example.rest_davidroldan.domain.modelo.Customer customer) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.rest_davidroldan.domain.modelo.Customer getCustomer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$DeleteCustomersSeleccionadas;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "()V", "app_debug"})
    public static final class DeleteCustomersSeleccionadas extends com.example.rest_davidroldan.framework.main.MainEvent {
        
        public DeleteCustomersSeleccionadas() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "()V", "app_debug"})
    public static final class ErrorVisto extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.MainEvent.ErrorVisto INSTANCE = null;
        
        private ErrorVisto() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$GetCustomers;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "()V", "app_debug"})
    public static final class GetCustomers extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.MainEvent.GetCustomers INSTANCE = null;
        
        private GetCustomers() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$GetCustomersFiltradas;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "filtro", "", "(Ljava/lang/String;)V", "getFiltro", "()Ljava/lang/String;", "app_debug"})
    public static final class GetCustomersFiltradas extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        private final java.lang.String filtro = null;
        
        public GetCustomersFiltradas(@org.jetbrains.annotations.NotNull
        java.lang.String filtro) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getFiltro() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$ResetSelectMode;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "()V", "app_debug"})
    public static final class ResetSelectMode extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.MainEvent.ResetSelectMode INSTANCE = null;
        
        private ResetSelectMode() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$SeleccionaCustomers;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "customer", "Lcom/example/rest_davidroldan/domain/modelo/Customer;", "(Lcom/example/rest_davidroldan/domain/modelo/Customer;)V", "getCustomer", "()Lcom/example/rest_davidroldan/domain/modelo/Customer;", "app_debug"})
    public static final class SeleccionaCustomers extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        private final com.example.rest_davidroldan.domain.modelo.Customer customer = null;
        
        public SeleccionaCustomers(@org.jetbrains.annotations.NotNull
        com.example.rest_davidroldan.domain.modelo.Customer customer) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.rest_davidroldan.domain.modelo.Customer getCustomer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/main/MainEvent$StartSelectMode;", "Lcom/example/rest_davidroldan/framework/main/MainEvent;", "()V", "app_debug"})
    public static final class StartSelectMode extends com.example.rest_davidroldan.framework.main.MainEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.main.MainEvent.StartSelectMode INSTANCE = null;
        
        private StartSelectMode() {
        }
    }
}