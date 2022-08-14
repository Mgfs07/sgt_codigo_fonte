package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GastoListMapper extends EntityMapper<GastoListDTO, Gasto> {

    @Override
    @Mapping(source = "colaborador.nomeColaborador", target = "nomeColaborador")
    @Mapping(source = "retiradoDe.nomePagamento", target = "retiradoDoPagamento")
    GastoListDTO toDto(Gasto gasto);

    @Override
    @InheritInverseConfiguration
    Gasto toEntity(GastoListDTO gastoListDTO);
}
