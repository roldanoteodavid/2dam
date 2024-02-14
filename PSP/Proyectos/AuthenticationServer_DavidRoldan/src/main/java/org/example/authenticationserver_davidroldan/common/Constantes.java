package org.example.authenticationserver_davidroldan.common;

public class Constantes {
    public static final String ID = "id";
    public static final String CONFIG_PROPERTIES = "config.properties";
    public static final String PATH_DATOS = "pathDatos";
    public static final String PASS = "pass";
    public static final String USERKEYSTORE = "userkeystore";
    public static final String CREDENTIALS = "credentials";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String USER_OR_PASSWORD_IS_EMPTY = "User or password empty";
    public static final String USER_OR_PASSWORD_INCORRECT = "User or password incorrect";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String TOKEN_EXPIRED = "Token expired";
    public static final String TOKEN_IS_EMPTY = "Token is empty";

    public static final int EXPIRATION_TIME_ACCESS_TOKEN = 180;
    public static final int EXPIRATION_TIME_REFRESH_TOKEN = 10800;


    private Constantes() {
    }
}
