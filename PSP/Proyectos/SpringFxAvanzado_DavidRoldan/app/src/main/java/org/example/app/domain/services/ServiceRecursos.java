package org.example.app.domain.services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import org.example.app.domain.modelo.Recurso;
import org.example.app.domain.modelo.errores.ErrorCliente;

import java.util.List;

public interface ServiceRecursos {
    Single<Either<ErrorCliente, List<Recurso>>> getRecursos(String username);
    Single<Either<ErrorCliente, Integer>> addRecurso(Recurso recurso);
    Single<Either<ErrorCliente, Recurso>> getPassword(int idrecurso, String username);
    Single<Either<ErrorCliente, Integer>> cambiarPassword(int idrecurso, String newpass, String username);
    Single<Either<ErrorCliente, Integer>> verificarFirma(int idrecurso, String username);
    Single<Either<ErrorCliente, Integer>> compartirRecurso(int idrecurso, String newusername, String actualuser);
}
