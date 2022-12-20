package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.PagamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PagamentoMapper extends EntityMapper<PagamentoDTO, Pagamento> {

}
