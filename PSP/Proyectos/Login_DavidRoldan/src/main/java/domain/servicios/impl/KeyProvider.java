package domain.servicios.impl;
import common.Constants;
import io.jsonwebtoken.security.Keys;
import jakarta.errores.AlgorithmNotSupportedException;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class KeyProvider {

    @Singleton
    @Produces
    public SecretKey getKey(){
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(Constants.SHA_512);
            digest.update(Constants.CLAVE.getBytes(StandardCharsets.UTF_8));
            final SecretKeySpec key2 = new SecretKeySpec(
                    digest.digest(), 0, 64, Constants.AES);
            return Keys.hmacShaKeyFor(key2.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new AlgorithmNotSupportedException(Constants.ALGORITHM_NOT_SUPPORTED, e);
        }
    }
}
