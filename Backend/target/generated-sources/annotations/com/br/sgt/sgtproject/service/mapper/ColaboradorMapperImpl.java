package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Unidade;
import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
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
public class ColaboradorMapperImpl implements ColaboradorMapper {

    @Override
    public List<Colaborador> toEntity(List<ColaboradorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Colaborador> list = new ArrayList<Colaborador>( dtoList.size() );
        for ( ColaboradorDTO colaboradorDTO : dtoList ) {
            list.add( toEntity( colaboradorDTO ) );
        }

        return list;
    }

    @Override
    public List<ColaboradorDTO> toDto(List<Colaborador> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ColaboradorDTO> list = new ArrayList<ColaboradorDTO>( entityList.size() );
        for ( Colaborador colaborador : entityList ) {
            list.add( toDto( colaborador ) );
        }

        return list;
    }

    @Override
    public ColaboradorDTO toDto(Colaborador colaborador) {
        if ( colaborador == null ) {
            return null;
        }

        Integer idUnidade = null;
        Integer id = null;
        String nomeColaborador = null;
        String telefone = null;
        String email = null;
        Boolean ativo = null;

        idUnidade = colaboradorUnidadeId( colaborador );
        id = colaborador.getId();
        nomeColaborador = colaborador.getNomeColaborador();
        telefone = colaborador.getTelefone();
        email = colaborador.getEmail();
        ativo = colaborador.getAtivo();

        ColaboradorDTO colaboradorDTO = new ColaboradorDTO( id, nomeColaborador, idUnidade, telefone, email, ativo );

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
