package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-14T01:31:23-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class PagamentoColaboradorListMapperImpl implements PagamentoColaboradorListMapper {

    @Override
    public List<PagamentoColaborador> toEntity(List<PagamentoColaboradorListDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PagamentoColaborador> list = new ArrayList<PagamentoColaborador>( arg0.size() );
        for ( PagamentoColaboradorListDTO pagamentoColaboradorListDTO : arg0 ) {
            list.add( toEntity( pagamentoColaboradorListDTO ) );
        }

        return list;
    }

    @Override
    public List<PagamentoColaboradorListDTO> toDto(List<PagamentoColaborador> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PagamentoColaboradorListDTO> list = new ArrayList<PagamentoColaboradorListDTO>( arg0.size() );
        for ( PagamentoColaborador pagamentoColaborador : arg0 ) {
            list.add( toDto( pagamentoColaborador ) );
        }

        return list;
    }

    @Override
    public PagamentoColaboradorListDTO toDto(PagamentoColaborador pagamentoColaborador) {
        if ( pagamentoColaborador == null ) {
            return null;
        }

        PagamentoColaboradorListDTO pagamentoColaboradorListDTO = new PagamentoColaboradorListDTO();

        pagamentoColaboradorListDTO.setNomeColaborador( pagamentoColaboradorColaboradorNomeColaborador( pagamentoColaborador ) );
        pagamentoColaboradorListDTO.setNomePagamento( pagamentoColaboradorPagamentoNomePagamento( pagamentoColaborador ) );
        pagamentoColaboradorListDTO.setId( pagamentoColaborador.getId() );
        pagamentoColaboradorListDTO.setDataPagamento( pagamentoColaborador.getDataPagamento() );
        pagamentoColaboradorListDTO.setValorPago( pagamentoColaborador.getValorPago() );

        return pagamentoColaboradorListDTO;
    }

    @Override
    public PagamentoColaborador toEntity(PagamentoColaboradorListDTO pagamentoColaboradorListDTO) {
        if ( pagamentoColaboradorListDTO == null ) {
            return null;
        }

        PagamentoColaborador pagamentoColaborador = new PagamentoColaborador();

        pagamentoColaborador.setColaborador( pagamentoColaboradorListDTOToColaborador( pagamentoColaboradorListDTO ) );
        pagamentoColaborador.setPagamento( pagamentoColaboradorListDTOToPagamento( pagamentoColaboradorListDTO ) );
        pagamentoColaborador.setId( pagamentoColaboradorListDTO.getId() );
        pagamentoColaborador.setDataPagamento( pagamentoColaboradorListDTO.getDataPagamento() );
        pagamentoColaborador.setValorPago( pagamentoColaboradorListDTO.getValorPago() );

        return pagamentoColaborador;
    }

    private String pagamentoColaboradorColaboradorNomeColaborador(PagamentoColaborador pagamentoColaborador) {
        if ( pagamentoColaborador == null ) {
            return null;
        }
        Colaborador colaborador = pagamentoColaborador.getColaborador();
        if ( colaborador == null ) {
            return null;
        }
        String nomeColaborador = colaborador.getNomeColaborador();
        if ( nomeColaborador == null ) {
            return null;
        }
        return nomeColaborador;
    }

    private String pagamentoColaboradorPagamentoNomePagamento(PagamentoColaborador pagamentoColaborador) {
        if ( pagamentoColaborador == null ) {
            return null;
        }
        Pagamento pagamento = pagamentoColaborador.getPagamento();
        if ( pagamento == null ) {
            return null;
        }
        String nomePagamento = pagamento.getNomePagamento();
        if ( nomePagamento == null ) {
            return null;
        }
        return nomePagamento;
    }

    protected Colaborador pagamentoColaboradorListDTOToColaborador(PagamentoColaboradorListDTO pagamentoColaboradorListDTO) {
        if ( pagamentoColaboradorListDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setNomeColaborador( pagamentoColaboradorListDTO.getNomeColaborador() );

        return colaborador;
    }

    protected Pagamento pagamentoColaboradorListDTOToPagamento(PagamentoColaboradorListDTO pagamentoColaboradorListDTO) {
        if ( pagamentoColaboradorListDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setNomePagamento( pagamentoColaboradorListDTO.getNomePagamento() );

        return pagamento;
    }
}