package br.gov.basis.eat.eatservice.service.mapper;

import br.gov.basis.eat.eatservice.domain.Registro;
import br.gov.basis.eat.eatservice.service.dto.RegistroDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroMapper extends EntityMapper<RegistroDTO, Registro>{
    @Override
    @Mapping(source = "impacto.id", target = "idImpacto")
    @Mapping(source = "tipoRegistro.id", target = "idTipoRegistro")
    @Mapping(source = "statusRegistro.id", target = "idStatusRegistro")
    @Mapping(source = "prioridade.id", target = "idPrioridade")
    @Mapping(source = "cliente.id", target = "idCliente")
    RegistroDTO toDto(Registro registro);

    @Override
    @InheritInverseConfiguration
    Registro toEntity(RegistroDTO registroDTO);
}
