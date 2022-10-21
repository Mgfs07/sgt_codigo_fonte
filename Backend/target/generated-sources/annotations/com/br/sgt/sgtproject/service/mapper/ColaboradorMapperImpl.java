package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Unidade;
import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T21:19:45-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class ColaboradorMapperImpl implements ColaboradorMapper {

    @Override
    public List<Colaborador> toEntity(List<ColaboradorDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Colaborador> list = new ArrayList<Colaborador>( arg0.size() );
        for ( ColaboradorDTO colaboradorDTO : arg0 ) {
            list.add( toEntity( colaboradorDTO ) );
        }

        return list;
    }

    @Override
    public List<ColaboradorDTO> toDto(List<Colaborador> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ColaboradorDTO> list = new ArrayList<ColaboradorDTO>( arg0.size() );
        for ( Colaborador colaborador : arg0 ) {
            list.add( toDto( colaborador ) );
        }

        return list;
    }

    @Override
    public ColaboradorDTO toDto(Colaborador colaborador) {
        if ( colaborador == null ) {
            return null;
        }

        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();

        colaboradorDTO.setIdUnidade( colaboradorUnidadeId( colaborador ) );
        colaboradorDTO.setId( colaborador.getId() );
        colaboradorDTO.setNomeColaborador( colaborador.getNomeColaborador() );
        colaboradorDTO.setTelefone( colaborador.getTelefone() );
        colaboradorDTO.setEmail( colaborador.getEmail() );
        colaboradorDTO.setAtivo( colaborador.getAtivo() );

        return colaboradorDTO;
    }

    @Override
    public Colaborador toEntity(ColaboradorDTO colaboradorDTO) {
        if ( colaboradorDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setUnidade( colaboradorDTOToUnidade( colaboradorDTO ) );
        colaborador.setId( colaboradorDTO.getId() );
        colaborador.setNomeColaborador( colaboradorDTO.getNomeColaborador() );
        colaborador.setTelefone( colaboradorDTO.getTelefone() );
        colaborador.setEmail( colaboradorDTO.getEmail() );
        colaborador.setAtivo( colaboradorDTO.getAtivo() );

        return colaborador;
    }

    private Integer colaboradorUnidadeId(Colaborador colaborador) {
        if ( colaborador == null ) {
            return null;
        }
        Unidade unidade = colaborador.getUnidade();
        if ( unidade == null ) {
            return null;
        }
        Integer id = unidade.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Unidade colaboradorDTOToUnidade(ColaboradorDTO colaboradorDTO) {
        if ( colaboradorDTO == null ) {
            return null;
        }

        Unidade unidade = new Unidade();

        unidade.setId( colaboradorDTO.getIdUnidade() );

        return unidade;
    }
}
