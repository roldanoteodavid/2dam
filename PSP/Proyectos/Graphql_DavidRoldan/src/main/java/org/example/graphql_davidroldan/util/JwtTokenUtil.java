package org.example.graphql_davidroldan.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.example.graphql_davidroldan.common.Constantes;
import org.example.graphql_davidroldan.config.Configuration;
import org.example.graphql_davidroldan.domain.modelo.errores.ValidationException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PublicKey;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final Configuration config;

    public boolean validate(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(token);

            long expirationMillis = claimsJws.getBody().getExpiration().getTime();
            return System.currentTimeMillis() < expirationMillis;

        } catch (ExpiredJwtException e) {
            throw new ValidationException("Token expirado");
        }
    }

    public String getUsername(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    //getRole
    public String getRole(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().get(Constantes.ROLE, String.class);
    }

    private PublicKey getPublicKey() {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(config.getPathDatos())) {
                ks.load(fis, config.getPassword().toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(config.getPassword().toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(config.getUserkeystore(), pt);
            if (pkEntry != null) {
                return pkEntry.getCertificate().getPublicKey();
            } else {
                throw new RuntimeException("No se encontró la entrada de clave privada en el keystore.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la clave pública del keystore.", e);
        }
    }
}
