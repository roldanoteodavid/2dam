package org.example.authenticationserver_davidroldan.domain.modelo.mappers;

import org.example.authenticationserver_davidroldan.data.modelo.CredentialsEntity;
import org.example.authenticationserver_davidroldan.domain.modelo.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsEntityMapper {
        CredentialsEntity toCredentialsEntity(Credentials credentials);
        Credentials toCredentials(CredentialsEntity credentialsEntity);
}
