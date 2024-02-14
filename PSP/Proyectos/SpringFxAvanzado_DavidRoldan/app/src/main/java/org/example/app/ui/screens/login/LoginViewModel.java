package org.example.app.ui.screens.login;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.example.app.common.Constantes;
import org.example.app.domain.modelo.Credentials;
import org.example.app.domain.services.ServiceCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginViewModel {

    private final ServiceCredentials serviceCredentials;

    private final ObjectProperty<LoginState> state;

    @Autowired
    public LoginViewModel(ServiceCredentials serviceCredentials) {
        this.serviceCredentials = serviceCredentials;
        state = new SimpleObjectProperty<>(new LoginState(false, null,null, false));
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void doLogin(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            state.set(new LoginState(false, Constantes.DEBE_COMPLETAR_TODOS_LOS_CAMPOS,null, false));
            return;
        }
        state.set(new LoginState(false, null,null, true));
        if (serviceCredentials.login(username, password)) {
            state.set(new LoginState(true, null,null, false));
        } else {
            state.set(new LoginState(false, Constantes.USUARIO_O_CONTRASENYA_INCORRECTOS,null, false));
        }
    }

    public void doSignIn(Credentials credentials) {
        if (credentials == null || credentials.getUsername() == null || credentials.getUsername().isEmpty() || credentials.getPassword() == null || credentials.getPassword().isEmpty()) {
            state.set(new LoginState(false, Constantes.DEBE_COMPLETAR_TODOS_LOS_CAMPOS,null,  false));
            return;
        }
        state.set(new LoginState(false, null,null, true));
        if (serviceCredentials.register(credentials.getUsername(), credentials.getPassword())) {
            state.set(new LoginState(false, null,Constantes.TE_HAS_REGISTRADO_COMO +credentials.getUsername(), false));
        } else {
            state.set(new LoginState(false, Constantes.EL_USUARIO_YA_EXISTE,null, false));
        }
    }

    public void cleanState() {
        state.set(new LoginState(false, null,null, false));
    }

}
