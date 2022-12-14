package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagamentoColaboradorListMapper extends EntityMapper<PagamentoColaboradorListDTO, PagamentoColaborador> {

    @Override
    @Mapping(source = "colaborador.nomeColaborador", target = "nomeColaborador")
    @Mapping(source = "pagamento.nomePagamento", target = "nomePagamento")
    PagamentoColaboradorListDTO toDto(PagamentoColaborador pagamentoColaborador);

    @Override
    @InheritInverseConfiguration
    PagamentoColaborador toEntity(PagamentoColaboradorListDTO pagamentoColaboradorListDTO);
}
