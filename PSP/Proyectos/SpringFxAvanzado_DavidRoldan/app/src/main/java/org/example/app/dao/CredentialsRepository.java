package org.example.app.dao;

import org.example.app.domain.modelo.Credentials;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends
        ListCrudRepository<Credentials, Integer> {

    Credentials findByUsername(String username);
}
