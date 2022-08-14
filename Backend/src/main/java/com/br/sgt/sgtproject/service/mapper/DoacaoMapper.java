package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.service.dto.DoacaoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoacaoMapper extends EntityMapper<DoacaoDTO, Doacao> {

    @Override
    @Mapping(source = "doadoPara.id", target = "doadoParaPagamento")
    DoacaoDTO toDto(Doacao doacao);

    @Override
    @InheritInverseConfiguration
    Doacao toEntity(DoacaoDTO doacaoDTO);
}
