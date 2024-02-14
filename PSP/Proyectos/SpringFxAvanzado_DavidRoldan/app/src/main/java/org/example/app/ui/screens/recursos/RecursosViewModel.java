package org.example.app.ui.screens.recursos;

import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.example.app.common.Constantes;
import org.example.app.domain.modelo.Recurso;
import org.example.app.domain.services.ServiceCredentials;
import org.example.app.domain.services.ServiceRecursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecursosViewModel {

    private final ServiceCredentials serviceCredentials;
    private final ServiceRecursos serviceRecursos;

    private final ObjectProperty<RecursosState> state;

    @Autowired
    public RecursosViewModel(ServiceCredentials serviceCredentials, ServiceRecursos serviceRecursos) {
        this.serviceCredentials = serviceCredentials;
        this.serviceRecursos = serviceRecursos;
        state = new SimpleObjectProperty<>(new RecursosState(null, null, null, null, false, null));
    }

    public ReadOnlyObjectProperty<RecursosState> getState() {
        return state;
    }

    public void getRecursos(String username) {
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceRecursos.getRecursos(username).observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft())
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            else {
                state.set(new RecursosState(null, either.get(), null, null, false, null));
            }
        });
    }

    public void getUsers(String username) {
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceCredentials.getUsers().observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft())
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            else {
                List<String> users = either.get();
                users.remove(username);
                state.set(new RecursosState(users, null, null, null, false, null));
            }
        });
    }

    public void addRecurso(Recurso recurso) {
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceRecursos.addRecurso(recurso).observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft())
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            else {
                state.set(new RecursosState(null, null, null, Constantes.RECURSO_ANYADIDO_CORRECTAMENTE, false, null));
            }
        });
        getRecursos(recurso.getUserFirma());
    }

    public void getPassword(int idrecurso, String username) {
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceRecursos.getPassword(idrecurso, username).observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft())
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            else {
                state.set(new RecursosState(null, null, null, null, false, either.get()));
            }
        });
    }

    public void cambiarPass(int idrecurso, String newpass, String username) {
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceRecursos.cambiarPassword(idrecurso, newpass, username).observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft())
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            else {
                state.set(new RecursosState(null, null, null, Constantes.PASSWORD_CAMBIADA_CORRECTAMENTE, false, null));
            }
        });
        getRecursos(username);
    }

    public void comprobarFirma(int idrecurso, String password) {
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceRecursos.verificarFirma(idrecurso, password).observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft()) {
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            } else {
                state.set(new RecursosState(null, null, null, Constantes.FIRMA_VERIFICADA_CORRECTAMENTE, false, null));
            }
        });
    }

    public void compartirRecurso(int idrecurso, String newuser, String actualuser){
        state.set(new RecursosState(null, null, null, null, true, null));
        serviceRecursos.compartirRecurso(idrecurso, newuser, actualuser).observeOn(Schedulers.single()).subscribe(either -> {
            if (either.isLeft()) {
                state.set(new RecursosState(null, null, either.getLeft().getError(), null, false, null));
            } else {
                state.set(new RecursosState(null, null, null, Constantes.RECURSO_COMPARTIDO + newuser, false, null));
            }
        });
    }

    public void cleanState() {
        state.set(new RecursosState(null, null, null, null, false, null));
    }
}
