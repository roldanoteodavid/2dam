package org.example.app.domain.services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import org.example.app.domain.modelo.errores.ErrorCliente;

import java.util.List;

public interface ServiceCredentials {

    boolean login(String username, String password);

    boolean register(String username, String password);
    Single<Either<ErrorCliente, List<String>>> getUsers();
}
