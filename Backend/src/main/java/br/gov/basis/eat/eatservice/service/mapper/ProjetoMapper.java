package br.gov.basis.eat.eatservice.service.mapper;

import br.gov.basis.eat.eatservice.domain.Projeto;
import br.gov.basis.eat.eatservice.service.dto.ProjetoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjetoMapper extends EntityMapper<ProjetoDTO, Projeto> {

    @Override
    @Mapping(source = "cliente.id", target = "idCliente")
    ProjetoDTO toDto(Projeto projeto);

    @Override
    @InheritInverseConfiguration
    Projeto toEntity(ProjetoDTO projetoDTO);

}
