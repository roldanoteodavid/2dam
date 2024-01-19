package dao;

import model.Credential;

public interface LoginDAO {
    boolean get(Credential credential);
}
