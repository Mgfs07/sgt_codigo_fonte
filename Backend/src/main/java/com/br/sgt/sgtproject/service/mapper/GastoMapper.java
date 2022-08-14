package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.service.dto.GastoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GastoMapper extends EntityMapper<GastoDTO, Gasto> {

    @Override
    @Mapping(source = "colaborador.id", target = "idColaborador")
    @Mapping(source = "retiradoDe.id", target = "retiradoDoPagamento")
    GastoDTO toDto(Gasto gasto);

    @Override
    @InheritInverseConfiguration
    Gasto toEntity(GastoDTO gastoDTO);
}
