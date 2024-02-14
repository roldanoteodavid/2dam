package org.example.graphql_davidroldan.domain.modelo.mappers;

import org.example.graphql_davidroldan.data.modelo.PremioEntity;
import org.example.graphql_davidroldan.domain.modelo.Premio;
import org.example.graphql_davidroldan.domain.modelo.graphql.PremioInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PremioEntityMapper {
    PremioEntity toPremioEntity(Premio premio);
    Premio toPremio(PremioEntity premioEntity);
    Premio toPremio(PremioInput premioInput);
}
