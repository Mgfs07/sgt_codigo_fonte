package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoacaoListMapper extends EntityMapper<DoacaoListDTO, Doacao>{

    @Override
    @Mapping(source = "pagamento.nomePagamento", target = "doadoParaPagamento")
    DoacaoListDTO toDto(Doacao doacao);

    @Override
    @InheritInverseConfiguration
    Doacao toEntity(DoacaoListDTO doacaoListDTO);
}
