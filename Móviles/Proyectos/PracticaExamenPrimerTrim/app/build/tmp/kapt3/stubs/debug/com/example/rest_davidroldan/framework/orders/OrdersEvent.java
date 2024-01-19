package com.example.rest_davidroldan.framework.orders;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/OrdersEvent;", "", "()V", "DeleteOrder", "ErrorVisto", "GetCustomersPorId", "GetOrders", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$DeleteOrder;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$GetCustomersPorId;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$GetOrders;", "app_debug"})
public abstract class OrdersEvent {
    
    private OrdersEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$DeleteOrder;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent;", "id", "", "(I)V", "getId", "()I", "app_debug"})
    public static final class DeleteOrder extends com.example.rest_davidroldan.framework.orders.OrdersEvent {
        private final int id = 0;
        
        public DeleteOrder(int id) {
        }
        
        public final int getId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$ErrorVisto;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent;", "()V", "app_debug"})
    public static final class ErrorVisto extends com.example.rest_davidroldan.framework.orders.OrdersEvent {
        @org.jetbrains.annotations.NotNull
        public static final com.example.rest_davidroldan.framework.orders.OrdersEvent.ErrorVisto INSTANCE = null;
        
        private ErrorVisto() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$GetCustomersPorId;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent;", "id", "", "(I)V", "getId", "()I", "app_debug"})
    public static final class GetCustomersPorId extends com.example.rest_davidroldan.framework.orders.OrdersEvent {
        private final int id = 0;
        
        public GetCustomersPorId(int id) {
        }
        
        public final int getId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/rest_davidroldan/framework/orders/OrdersEvent$GetOrders;", "Lcom/example/rest_davidroldan/framework/orders/OrdersEvent;", "()V", "app_debug"})
    public static final class GetOrders extends com.example.rest_davidroldan.framework.orders.OrdersEvent {
        
        public GetOrders() {
        }
    }
}