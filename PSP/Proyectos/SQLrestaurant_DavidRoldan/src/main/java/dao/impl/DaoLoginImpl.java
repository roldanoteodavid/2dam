package dao.impl;

import model.Credential;

public class DaoLoginImpl implements dao.LoginDAO {


    @Override
    public boolean get(Credential credential) {
        boolean valid = credential.getNombre().equals("root") && credential.getPassword().equals("2dam");
        return valid;
    }
}
