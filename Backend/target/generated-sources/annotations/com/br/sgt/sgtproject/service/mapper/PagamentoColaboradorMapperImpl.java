package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-14T21:54:08-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class PagamentoColaboradorMapperImpl implements PagamentoColaboradorMapper {

    @Override
    public List<PagamentoColaborador> toEntity(List<PagamentoColaboradorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PagamentoColaborador> list = new ArrayList<PagamentoColaborador>( dtoList.size() );
        for ( PagamentoColaboradorDTO pagamentoColaboradorDTO : dtoList ) {
            list.add( toEntity( pagamentoColaboradorDTO ) );
        }

        return list;
    }

    @Override
    public List<PagamentoColaboradorDTO> toDto(List<PagamentoColaborador> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PagamentoColaboradorDTO> list = new ArrayList<PagamentoColaboradorDTO>( entityList.size() );
        for ( PagamentoColaborador pagamentoColaborador : entityList ) {
            list.add( toDto( pagamentoColaborador ) );
        }

        return list;
    }

    @Override
    public PagamentoColaboradorDTO toDto(PagamentoColaborador pagamentoColaborador) {
        if ( pagamentoColaborador == null ) {
            return null;
        }

        PagamentoColaboradorDTO pagamentoColaboradorDTO = new PagamentoColaboradorDTO();

        pagamentoColaboradorDTO.setIdColaborador( pagamentoColaboradorColaboradorId( pagamentoColaborador ) );
        pagamentoColaboradorDTO.setIdPagamento( pagamentoColaboradorPagamentoId( pagamentoColaborador ) );
        pagamentoColaboradorDTO.setPagamentosRetirado( pagamentoColaboradorPagamentoId( pagamentoColaborador ) );
        pagamentoColaboradorDTO.setId( pagamentoColaborador.getId() );
        pagamentoColaboradorDTO.setObservacao( pagamentoColaborador.getObservacao() );
        pagamentoColaboradorDTO.setRetiradoLugar( pagamentoColaborador.getRetiradoLugar() );
        pagamentoColaboradorDTO.setDataPagamento( pagamentoColaborador.getDataPagamento() );
        pagamentoColaboradorDTO.setValorPago( pagamentoColaborador.getValorPago() );

        return pagamentoColaboradorDTO;
    }

    @Override
    public PagamentoColaborador toEntity(PagamentoColaboradorDTO pagamentoColaboradorDTO) {
        if ( pagamentoColaboradorDTO == null ) {
            return null;
        }

        PagamentoColaborador pagamentoColaborador = new PagamentoColaborador();

        pagamentoColaborador.setColaborador( pagamentoColaboradorDTOToColaborador( pagamentoColaboradorDTO ) );
        pagamentoColaborador.setPagamento( pagamentoColaboradorDTOToPagamento( pagamentoColaboradorDTO ) );
        pagamentoColaborador.setId( pagamentoColaboradorDTO.getId() );
        pagamentoColaborador.setObservacao( pagamentoColaboradorDTO.getObservacao() );
        pagamentoColaborador.setRetiradoLugar( pagamentoColaboradorDTO.getRetiradoLugar() );
        pagamentoColaborador.setDataPagamento( pagamentoColaboradorDTO.getDataPagamento() );
        pagamentoColaborador.setValorPago( pagamentoColaboradorDTO.getValorPago() );

        return pagamentoColaborador;
    }

    private Integer pagamentoColaboradorColaboradorId(PagamentoColaborador pagamentoColaborador) {
        if ( pagamentoColaborador == null ) {
            return null;
        }
        Colaborador colaborador = pagamentoColaborador.getColaborador();
        if ( colaborador == null ) {
            return null;
        }
        Integer id = colaborador.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer pagamentoColaboradorPagamentoId(PagamentoColaborador pagamentoColaborador) {
        if ( pagamentoColaborador == null ) {
            return null;
        }
        Pagamento pagamento = pagamentoColaborador.getPagamento();
        if ( pagamento == null ) {
            return null;
        }
        Integer id = pagamento.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Colaborador pagamentoColaboradorDTOToColaborador(PagamentoColaboradorDTO pagamentoColaboradorDTO) {
        if ( pagamentoColaboradorDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setId( pagamentoColaboradorDTO.getIdColaborador() );

        return colaborador;
    }

    protected Pagamento pagamentoColaboradorDTOToPagamento(PagamentoColaboradorDTO pagamentoColaboradorDTO) {
        if ( pagamentoColaboradorDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setId( pagamentoColaboradorDTO.getIdPagamento() );

        return pagamento;
    }
}
