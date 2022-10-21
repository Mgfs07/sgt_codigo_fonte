package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper extends EntityMapper<ColaboradorDTO, Colaborador> {

    @Override
    @Mapping(source = "unidade.id", target = "idUnidade")
    ColaboradorDTO toDto(Colaborador colaborador);

    @Override
    @InheritInverseConfiguration
    Colaborador toEntity(ColaboradorDTO colaboradorDTO);
}
