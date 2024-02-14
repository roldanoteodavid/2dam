package org.example.app.domain.services.impl;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.example.app.common.Constantes;
import org.example.app.config.Configuracion;
import org.example.app.dao.CredentialsRepository;
import org.example.app.domain.modelo.Credentials;
import org.example.app.domain.modelo.errores.ErrorCliente;
import org.example.app.domain.services.ServiceCredentials;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ServiceCredentialsImpl implements ServiceCredentials {

    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final Configuracion config;

    public ServiceCredentialsImpl(CredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder, Configuracion config) {
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.config = config;
    }

    @Override
    public boolean login(String username, String password) {
        boolean login = false;
        Credentials userCredentials = credentialsRepository.findByUsername(username);
        if (userCredentials != null) {
            login = passwordEncoder.matches(password, userCredentials.getPassword());
        }
        return login;
    }

    @Override
    public boolean register(String username, String password) {
        boolean register = false;
        Credentials userCredentials = credentialsRepository.findByUsername(username);
        if (userCredentials == null) {
            Credentials credentials = new Credentials();
            credentials.setUsername(username);
            credentials.setPassword(passwordEncoder.encode(password));
            credentialsRepository.save(credentials);
            KeyPair keyPair = generateKeyPair();
            saveKeyPairToKeystore(username, keyPair);

            register = true;
        }
        return register;
    }

    @Override
    public Single<Either<ErrorCliente, List<String>>> getUsers() {
        Either<ErrorCliente, List<String>> result = null;
        List<String> user = new ArrayList<>();
        credentialsRepository.findAll().forEach(userCredentials -> user.add(userCredentials.getUsername()));
        if (user.isEmpty()) {
            result = Either.left(new ErrorCliente(Constantes.NO_HAY_USUARIOS));
        } else {
            result = Either.right(user);
        }
        return Single.just(result);

    }


    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Constantes.RSA);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
        }
        return null;
    }

    private void saveKeyPairToKeystore(String username, KeyPair keyPair) {
        try {
            KeyStore ks = loadKeyStore();
            X509Certificate certificate = generateSelfSignedCertificate(username, keyPair.getPublic(), keyPair.getPrivate());

            // Almacenar la clave privada y el certificado en el keystore
            ks.setKeyEntry(username, keyPair.getPrivate(), config.getPassword().toCharArray(), new Certificate[]{certificate});

            storeKeyStore(ks);
        } catch (Exception e) {
            log.error(Constantes.ERROR_AL_GUARDAR_EL_PAR_DE_CLAVES_EN_EL_KEYSTORE, e);
        }
    }

    private KeyStore loadKeyStore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
        char[] keystorePassword = config.getPassword().toCharArray();
        try (FileInputStream fis = new FileInputStream(config.getPathDatos())) {
            ks.load(fis, keystorePassword);
        } catch (FileNotFoundException e) {
            ks.load(null, null);
        }
        return ks;
    }

    private void storeKeyStore(KeyStore ks) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
        try (FileOutputStream fos = new FileOutputStream(config.getPathDatos())) {
            ks.store(fos, config.getPassword().toCharArray());
        }
    }


    private X509Certificate generateSelfSignedCertificate(String username, PublicKey publicKey, PrivateKey privateKey) throws CertificateException, OperatorCreationException {
        X500Name owner = new X500Name(Constantes.CN + username);
        X500Name issuer = owner;

        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                issuer,
                BigInteger.valueOf(1),
                new Date(),
                new Date(System.currentTimeMillis() + 1000000),
                owner, SubjectPublicKeyInfo.getInstance(
                ASN1Sequence.getInstance(publicKey.getEncoded()))
        );

        ContentSigner signer = new JcaContentSignerBuilder(Constantes.SHA_256_WITH_RSA_ENCRYPTION).build(privateKey);
        X509CertificateHolder certHolder = certGen.build(signer);
        return new JcaX509CertificateConverter().getCertificate(certHolder);
    }
}
