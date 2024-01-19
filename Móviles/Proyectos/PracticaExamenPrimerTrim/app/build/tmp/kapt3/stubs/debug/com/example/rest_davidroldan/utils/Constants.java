package com.example.rest_davidroldan.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/rest_davidroldan/utils/Constants;", "", "()V", "BASE_URL", "", "CONTENTTYPE", "CUSTOMERDELETED", "CUSTOMERID", "CUSTOMERID_URL", "CUSTOMER_URL", "DATE", "DOB", "EMAIL", "ERROR", "ERRORADDINGORDER", "FIRSTNAME", "HASORDERS", "ID", "LASTNAME", "OF", "ORDERADDED", "ORDERDELETED", "ORDERID_URL", "ORDER_URL", "PHONE", "SELECTED", "TABLEID", "TOTALDELETED", "app_debug"})
public final class Constants {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String BASE_URL = "http://informatica.iesquevedo.es:2326/ServidorRest_DavidRoldan-1.0-SNAPSHOT/";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CUSTOMER_URL = "api/customers";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CUSTOMERID_URL = "api/customers/{id}";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ORDER_URL = "api/orders";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ORDERID_URL = "api/orders/{id}";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CONTENTTYPE = "Content-Type: application/json";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ERROR = "Error";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String HASORDERS = "\nThe customer has orders";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String SELECTED = "selected";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ID = "id";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String FIRSTNAME = "first_name";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String LASTNAME = "last_name";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String EMAIL = "email";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PHONE = "phone";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DOB = "dob";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TABLEID = "tableid";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DATE = "date";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CUSTOMERID = "customerid";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TOTALDELETED = "Total deleted ";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String OF = " of ";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CUSTOMERDELETED = "Customer deleted";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ORDERADDED = "Order added successfully";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ERRORADDINGORDER = "Error adding order";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ORDERDELETED = "Order deleted";
    @org.jetbrains.annotations.NotNull
    public static final com.example.rest_davidroldan.utils.Constants INSTANCE = null;
    
    private Constants() {
        super();
    }
}