package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PagamentoMapper.class, ColaboradorMapper.class})
public interface PagamentoColaboradorMapper extends EntityMapper<PagamentoColaboradorDTO, PagamentoColaborador> {


    @Override
    @Mapping(source = "colaborador.id", target = "idColaborador")
    @Mapping(source = "pagamento.id", target = "idPagamento")
    @Mapping(source = "pagamento.id", target = "pagamentosRetirado")
    PagamentoColaboradorDTO toDto(PagamentoColaborador pagamentoColaborador);

    @Override
    @InheritInverseConfiguration
    PagamentoColaborador toEntity(PagamentoColaboradorDTO pagamentoColaboradorDTO);
}
