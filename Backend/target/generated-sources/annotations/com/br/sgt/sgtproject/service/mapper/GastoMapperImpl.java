package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.GastoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-19T17:28:22-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class GastoMapperImpl implements GastoMapper {

    @Override
    public List<Gasto> toEntity(List<GastoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Gasto> list = new ArrayList<Gasto>( dtoList.size() );
        for ( GastoDTO gastoDTO : dtoList ) {
            list.add( toEntity( gastoDTO ) );
        }

        return list;
    }

    @Override
    public List<GastoDTO> toDto(List<Gasto> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GastoDTO> list = new ArrayList<GastoDTO>( entityList.size() );
        for ( Gasto gasto : entityList ) {
            list.add( toDto( gasto ) );
        }

        return list;
    }

    @Override
    public GastoDTO toDto(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }

        GastoDTO gastoDTO = new GastoDTO();

        gastoDTO.setIdColaborador( gastoColaboradorId( gasto ) );
        gastoDTO.setRetiradoDoPagamento( gastoRetiradoDeId( gasto ) );
        gastoDTO.setId( gasto.getId() );
        gastoDTO.setMotivo( gasto.getMotivo() );
        gastoDTO.setDescricao( gasto.getDescricao() );
        gastoDTO.setDataDispesa( gasto.getDataDispesa() );
        gastoDTO.setValorRetirado( gasto.getValorRetirado() );
        gastoDTO.setComprovante( gasto.getComprovante() );

        return gastoDTO;
    }

    @Override
    public Gasto toEntity(GastoDTO gastoDTO) {
        if ( gastoDTO == null ) {
            return null;
        }

        Gasto gasto = new Gasto();

        gasto.setColaborador( gastoDTOToColaborador( gastoDTO ) );
        gasto.setRetiradoDe( gastoDTOToPagamento( gastoDTO ) );
        gasto.setId( gastoDTO.getId() );
        gasto.setMotivo( gastoDTO.getMotivo() );
        gasto.setDescricao( gastoDTO.getDescricao() );
        gasto.setDataDispesa( gastoDTO.getDataDispesa() );
        gasto.setValorRetirado( gastoDTO.getValorRetirado() );
        gasto.setComprovante( gastoDTO.getComprovante() );

        return gasto;
    }

    private Integer gastoColaboradorId(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }
        Colaborador colaborador = gasto.getColaborador();
        if ( colaborador == null ) {
            return null;
        }
        Integer id = colaborador.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer gastoRetiradoDeId(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }
        Pagamento retiradoDe = gasto.getRetiradoDe();
        if ( retiradoDe == null ) {
            return null;
        }
        Integer id = retiradoDe.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Colaborador gastoDTOToColaborador(GastoDTO gastoDTO) {
        if ( gastoDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setId( gastoDTO.getIdColaborador() );

        return colaborador;
    }

    protected Pagamento gastoDTOToPagamento(GastoDTO gastoDTO) {
        if ( gastoDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setId( gastoDTO.getRetiradoDoPagamento() );

        return pagamento;
    }
}
