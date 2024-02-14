package org.example.app.seguridad;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface Encriptacion {

    String encriptar(String texto,String secret);
    String encriptar(String strToEncrypt, PublicKey publicKey);

    String desencriptar(String texto,String secret);
    String desencriptar(String strToDecrypt, PrivateKey privateKey);

}
