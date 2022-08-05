package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;

public interface GastoListMapper extends EntityMapper<GastoListDTO, Gasto> {

    @Override
    @Mapping(source = "colaborador.nomeColaborador", target = "NomeColaborador")
    @Mapping(source = "pagamento.nomePagamento", target = "retiradoDoPagamento")
    GastoListDTO toDto(Gasto gasto);

    @Override
    @InheritInverseConfiguration
    Gasto toEntity(GastoListDTO gastoListDTO);
}
