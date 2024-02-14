package org.example.app.domain.services.impl;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import org.example.app.common.Constantes;
import org.example.app.config.Configuracion;
import org.example.app.dao.RecursosRepository;
import org.example.app.dao.VisualizadoresRepository;
import org.example.app.domain.modelo.Recurso;
import org.example.app.domain.modelo.Visualizador;
import org.example.app.domain.modelo.errores.ErrorCliente;
import org.example.app.domain.services.ServiceRecursos;
import org.example.app.seguridad.Encriptacion;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ServiceRecursosImpl implements ServiceRecursos {

    private final Encriptacion encriptacion;
    private final Configuracion config;
    private final RecursosRepository recursosRepository;
    private final VisualizadoresRepository visualizadoresRepository;


    public ServiceRecursosImpl(Encriptacion encriptacion, Configuracion config, RecursosRepository recursosRepository, VisualizadoresRepository visualizadoresRepository) {
        this.encriptacion = encriptacion;
        this.config = config;
        this.recursosRepository = recursosRepository;
        this.visualizadoresRepository = visualizadoresRepository;
    }

    @Override
    public Single<Either<ErrorCliente, List<Recurso>>> getRecursos(String username) {
        Either<ErrorCliente, List<Recurso>> result = null;
        List<Visualizador> visualizadores = visualizadoresRepository.findByUsername(username);
        List<Recurso> recursos = new ArrayList<>();
        visualizadores.forEach(visualizador -> recursos.add(recursosRepository.findById(visualizador.getRecurso().getId()).orElse(null)));
        if (!recursos.isEmpty()) {
            result = Either.right(recursos);
        } else {
            result = Either.left(new ErrorCliente(Constantes.NO_HAY_RECURSOS));
        }

        return Single.just(result);
    }

    @Override
    public Single<Either<ErrorCliente, Integer>> addRecurso(Recurso recurso) {
        Either<ErrorCliente, Integer> result = null;
        String firma = null;
        try {
            firma = generarFirma(recurso.getUserFirma(), recurso.getPassword());
        } catch (Exception e) {
            return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_GENERAR_LA_FIRMA)));
        }
        String random = Utils.randomBytes();
        String encriptpassword = encriptacion.encriptar(recurso.getPassword(), random);
        recurso.setPassword(encriptpassword);

        recurso.setFirma(firma);
        recursosRepository.save(recurso);

        PublicKey publicKey = null;
        try {
            publicKey = getClavePublicaUsuario(recurso.getUserFirma());
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException e) {
            return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_OBTENER_LA_CLAVE_PUBLICA)));
        }

        String randomCifrado = encriptacion.encriptar(random, publicKey);

        Visualizador visualizador = new Visualizador();
        visualizador.setUsername(recurso.getUserFirma());
        visualizador.setPassword(randomCifrado);
        visualizador.setRecurso(recurso);

        visualizadoresRepository.save(visualizador);
        result = Either.right(1);

        return Single.just(result);
    }

    @Override
    public Single<Either<ErrorCliente, Recurso>> getPassword(int idrecurso, String username) {
        Either<ErrorCliente, Recurso> result = null;
        Recurso recurso = recursosRepository.findById(idrecurso).orElse(null);
        if (recurso != null) {
            try {
                PrivateKey privateKey = getPrivateKeyUser(username);
                Visualizador visualizador = visualizadoresRepository.findByRecursoIdAndUsername(idrecurso, username);
                String random = encriptacion.desencriptar(visualizador.getPassword(), privateKey);
                String password = encriptacion.desencriptar(recurso.getPassword(), random);
                recurso.setPassword(password);
                result = Either.right(recurso);
            } catch (Exception e) {
                return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_OBTENER_LA_CLAVE_PRIVADA)));
            }
        } else {
            result = Either.left(new ErrorCliente(Constantes.RECURSO_NO_ENCONTRADO));
        }
        return Single.just(result);
    }

    @Override
    public Single<Either<ErrorCliente, Integer>> cambiarPassword(int idrecurso, String newpass, String username) {
        Either<ErrorCliente, Integer> result = null;
        Recurso recurso = recursosRepository.findById(idrecurso).orElse(null);
        Visualizador visualizador = visualizadoresRepository.findByRecursoIdAndUsername(idrecurso, username);
        if (recurso != null) {
            PrivateKey privateKey = null;
            try {
                privateKey = getPrivateKeyUser(username);
            } catch (Exception e) {
                return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_OBTENER_LA_CLAVE_PRIVADA)));
            }
            String random = encriptacion.desencriptar(visualizador.getPassword(), privateKey);
            String encriptpassword = encriptacion.encriptar(newpass, random);
            recurso.setPassword(encriptpassword);
            recurso.setUserFirma(username);
            //firma
            String firma = null;
            try {
                firma = generarFirma(username, newpass);
            } catch (Exception e) {
                return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_GENERAR_LA_FIRMA)));
            }
            recurso.setFirma(firma);
            recursosRepository.save(recurso);
            result = Either.right(1);
        } else {
            result = Either.left(new ErrorCliente(Constantes.RECURSO_NO_ENCONTRADO));
        }

        return Single.just(result);
    }

    @Override
    public Single<Either<ErrorCliente, Integer>> verificarFirma(int idrecurso, String password) {
        Either<ErrorCliente, Integer> result = null;
        try {
            Recurso recurso = recursosRepository.findById(idrecurso).orElse(null);

            if (recurso != null) {
                if (verifyFirma(recurso.getUserFirma(), password, recurso.getFirma())) {
                    result = Either.right(1);
                } else {
                    result = Either.left(new ErrorCliente(Constantes.LA_FIRMA_NO_ES_VALIDA));
                }
            } else {
                result = Either.left(new ErrorCliente(Constantes.RECURSO_NO_ENCONTRADO));
            }
        } catch (Exception e) {
            result = Either.left(new ErrorCliente(Constantes.LA_FIRMA_NO_ES_VALIDA));
        }
        return Single.just(result);
    }

    @Override
    public Single<Either<ErrorCliente, Integer>> compartirRecurso(int idrecurso, String newusername, String actualuser) {
        Either<ErrorCliente, Integer> result = null;
        Visualizador actualvisualizador = visualizadoresRepository.findByRecursoIdAndUsername(idrecurso, actualuser);
        Visualizador newvisualizadordb = visualizadoresRepository.findByRecursoIdAndUsername(idrecurso, newusername);
        if (newvisualizadordb != null) {
            return Single.just(Either.left(new ErrorCliente(Constantes.EL_USUARIO_YA_TIENE_ACCESO_AL_RECURSO)));
        }
        Recurso recurso = recursosRepository.findById(idrecurso).orElse(null);
        if (actualvisualizador != null) {
            PrivateKey privateKey = null;
            try {
                privateKey = getPrivateKeyUser(actualuser);
            } catch (Exception e) {
                return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_OBTENER_LA_CLAVE_PRIVADA)));
            }
            String random = encriptacion.desencriptar(actualvisualizador.getPassword(), privateKey);
            PublicKey publicKey = null;
            try {
                publicKey = getClavePublicaUsuario(newusername);
            } catch (Exception e) {
                return Single.just(Either.left(new ErrorCliente(Constantes.ERROR_AL_OBTENER_LA_CLAVE_PUBLICA)));
            }
            Visualizador newvisualizador = new Visualizador();
            newvisualizador.setUsername(newusername);
            newvisualizador.setRecurso(recurso);
            newvisualizador.setPassword(encriptacion.encriptar(random, publicKey));
            visualizadoresRepository.save(newvisualizador);
            result = Either.right(1);
        } else {
            result = Either.left(new ErrorCliente(Constantes.EL_USUARIO_NO_EXISTE));
        }
        return Single.just(result);
    }

    private String generarFirma(String userFirma, String passrecurso) throws NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance(Constantes.SHA_256_WITH_RSA);
        sign.initSign(getPrivateKeyUser(userFirma));
        MessageDigest hash = MessageDigest.getInstance(Constantes.SHA_256);
        sign.update(hash.digest(passrecurso.getBytes()));
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    private boolean verifyFirma(String userFirma, String passrecurso, String firma) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, InvalidKeyException, SignatureException {
        byte[] firmaBytes = Base64.getDecoder().decode(firma);
        Signature signature = Signature.getInstance(Constantes.SHA_256_WITH_RSA);
        signature.initVerify(getClavePublicaUsuario(userFirma));
        MessageDigest hash = MessageDigest.getInstance(Constantes.SHA_256);
        signature.update(hash.digest(passrecurso.getBytes()));
        return signature.verify(firmaBytes);
    }

    private PrivateKey getPrivateKeyUser(String username) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException {
        KeyStore ksLoad = KeyStore.getInstance(Constantes.PKCS_12);
        try (FileInputStream fis = new FileInputStream(config.getPathDatos())) {
            ksLoad.load(fis, config.getPassword().toCharArray());
        }

        KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(config.getPassword().toCharArray());

        KeyStore.Entry entry;
        entry = ksLoad.getEntry(username, pt);


        return ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
    }

    private PublicKey getClavePublicaUsuario(String username) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
        FileInputStream fis = new FileInputStream(config.getPathDatos());
        ks.load(fis, config.getPassword().toCharArray());

        X509Certificate cert = (X509Certificate) ks.getCertificate(username);
        return cert.getPublicKey();
    }


}
