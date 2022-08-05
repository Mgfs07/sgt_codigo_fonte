package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColaboradorListMapper extends EntityMapper<ColaboradorListDTO, Colaborador> {

    @Override
    @Mapping(source = "unidade.nomeUnidade", target = "nomeUnidade")
    ColaboradorListDTO toDto(Colaborador colaborador);

    @Override
    @InheritInverseConfiguration
    Colaborador toEntity(ColaboradorListDTO colaboradorListDTO);
}
