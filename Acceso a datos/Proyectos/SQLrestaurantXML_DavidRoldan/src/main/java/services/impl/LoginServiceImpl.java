package services.impl;

import dao.LoginDAO;
import jakarta.inject.Inject;
import model.Credential;
import services.LoginService;

public class LoginServiceImpl implements LoginService {
    @Inject
    private LoginDAO dao;

    @Override
    public boolean get(Credential credential) {
        return dao.get(credential);
    }
}
