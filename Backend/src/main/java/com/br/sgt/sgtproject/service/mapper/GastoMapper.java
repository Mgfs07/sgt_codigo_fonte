package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.service.dto.GastoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;

public interface GastoMapper extends EntityMapper<GastoDTO, Gasto> {

    @Override
    @Mapping(source = "colaborador.id", target = "idColaborador")
    @Mapping(source = "pagamento.id", target = "retiradoDoPagamento")
    GastoDTO toDto(Gasto gasto);

    @Override
    @InheritInverseConfiguration
    Gasto toEntity(GastoDTO gastoDTO);
}
