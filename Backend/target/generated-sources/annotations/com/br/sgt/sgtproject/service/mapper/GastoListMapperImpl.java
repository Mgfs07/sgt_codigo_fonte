package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
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
public class GastoListMapperImpl implements GastoListMapper {

    @Override
    public List<Gasto> toEntity(List<GastoListDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Gasto> list = new ArrayList<Gasto>( dtoList.size() );
        for ( GastoListDTO gastoListDTO : dtoList ) {
            list.add( toEntity( gastoListDTO ) );
        }

        return list;
    }

    @Override
    public List<GastoListDTO> toDto(List<Gasto> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GastoListDTO> list = new ArrayList<GastoListDTO>( entityList.size() );
        for ( Gasto gasto : entityList ) {
            list.add( toDto( gasto ) );
        }

        return list;
    }

    @Override
    public GastoListDTO toDto(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }

        GastoListDTO gastoListDTO = new GastoListDTO();

        gastoListDTO.setNomeColaborador( gastoColaboradorNomeColaborador( gasto ) );
        gastoListDTO.setRetiradoDoPagamento( gastoRetiradoDeNomePagamento( gasto ) );
        gastoListDTO.setId( gasto.getId() );
        gastoListDTO.setMotivo( gasto.getMotivo() );
        gastoListDTO.setDataDispesa( gasto.getDataDispesa() );
        gastoListDTO.setValorRetirado( gasto.getValorRetirado() );

        return gastoListDTO;
    }

    @Override
    public Gasto toEntity(GastoListDTO gastoListDTO) {
        if ( gastoListDTO == null ) {
            return null;
        }

        Gasto gasto = new Gasto();

        gasto.setColaborador( gastoListDTOToColaborador( gastoListDTO ) );
        gasto.setRetiradoDe( gastoListDTOToPagamento( gastoListDTO ) );
        gasto.setId( gastoListDTO.getId() );
        gasto.setMotivo( gastoListDTO.getMotivo() );
        gasto.setDataDispesa( gastoListDTO.getDataDispesa() );
        gasto.setValorRetirado( gastoListDTO.getValorRetirado() );

        return gasto;
    }

    private String gastoColaboradorNomeColaborador(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }
        Colaborador colaborador = gasto.getColaborador();
        if ( colaborador == null ) {
            return null;
        }
        String nomeColaborador = colaborador.getNomeColaborador();
        if ( nomeColaborador == null ) {
            return null;
        }
        return nomeColaborador;
    }

    private String gastoRetiradoDeNomePagamento(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }
        Pagamento retiradoDe = gasto.getRetiradoDe();
        if ( retiradoDe == null ) {
            return null;
        }
        String nomePagamento = retiradoDe.getNomePagamento();
        if ( nomePagamento == null ) {
            return null;
        }
        return nomePagamento;
    }

    protected Colaborador gastoListDTOToColaborador(GastoListDTO gastoListDTO) {
        if ( gastoListDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setNomeColaborador( gastoListDTO.getNomeColaborador() );

        return colaborador;
    }

    protected Pagamento gastoListDTOToPagamento(GastoListDTO gastoListDTO) {
        if ( gastoListDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setNomePagamento( gastoListDTO.getRetiradoDoPagamento() );

        return pagamento;
    }
}
