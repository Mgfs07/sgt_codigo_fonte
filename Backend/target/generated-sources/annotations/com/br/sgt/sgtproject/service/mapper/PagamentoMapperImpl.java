package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.PagamentoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-03T20:43:12-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class PagamentoMapperImpl implements PagamentoMapper {

    @Override
    public Pagamento toEntity(PagamentoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setId( dto.getId() );
        pagamento.setNomePagamento( dto.getNomePagamento() );
        pagamento.setValorMeta( dto.getValorMeta() );
        pagamento.setAtivo( dto.getAtivo() );

        return pagamento;
    }

    @Override
    public List<Pagamento> toEntity(List<PagamentoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Pagamento> list = new ArrayList<Pagamento>( dtoList.size() );
        for ( PagamentoDTO pagamentoDTO : dtoList ) {
            list.add( toEntity( pagamentoDTO ) );
        }

        return list;
    }

    @Override
    public PagamentoDTO toDto(Pagamento entity) {
        if ( entity == null ) {
            return null;
        }

        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        pagamentoDTO.setId( entity.getId() );
        pagamentoDTO.setNomePagamento( entity.getNomePagamento() );
        pagamentoDTO.setValorMeta( entity.getValorMeta() );
        pagamentoDTO.setAtivo( entity.getAtivo() );

        return pagamentoDTO;
    }

    @Override
    public List<PagamentoDTO> toDto(List<Pagamento> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PagamentoDTO> list = new ArrayList<PagamentoDTO>( entityList.size() );
        for ( Pagamento pagamento : entityList ) {
            list.add( toDto( pagamento ) );
        }

        return list;
    }
}
