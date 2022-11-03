package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Unidade;
import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-27T09:59:43-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class ColaboradorListMapperImpl implements ColaboradorListMapper {

    @Override
    public List<Colaborador> toEntity(List<ColaboradorListDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Colaborador> list = new ArrayList<Colaborador>( dtoList.size() );
        for ( ColaboradorListDTO colaboradorListDTO : dtoList ) {
            list.add( toEntity( colaboradorListDTO ) );
        }

        return list;
    }

    @Override
    public List<ColaboradorListDTO> toDto(List<Colaborador> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ColaboradorListDTO> list = new ArrayList<ColaboradorListDTO>( entityList.size() );
        for ( Colaborador colaborador : entityList ) {
            list.add( toDto( colaborador ) );
        }

        return list;
    }

    @Override
    public ColaboradorListDTO toDto(Colaborador colaborador) {
        if ( colaborador == null ) {
            return null;
        }

        ColaboradorListDTO colaboradorListDTO = new ColaboradorListDTO();

        colaboradorListDTO.setNomeUnidade( colaboradorUnidadeNomeUnidade( colaborador ) );
        colaboradorListDTO.setId( colaborador.getId() );
        colaboradorListDTO.setNomeColaborador( colaborador.getNomeColaborador() );
        colaboradorListDTO.setTelefone( colaborador.getTelefone() );

        return colaboradorListDTO;
    }

    @Override
    public Colaborador toEntity(ColaboradorListDTO colaboradorListDTO) {
        if ( colaboradorListDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setUnidade( colaboradorListDTOToUnidade( colaboradorListDTO ) );
        colaborador.setId( colaboradorListDTO.getId() );
        colaborador.setNomeColaborador( colaboradorListDTO.getNomeColaborador() );
        colaborador.setTelefone( colaboradorListDTO.getTelefone() );

        return colaborador;
    }

    private String colaboradorUnidadeNomeUnidade(Colaborador colaborador) {
        if ( colaborador == null ) {
            return null;
        }
        Unidade unidade = colaborador.getUnidade();
        if ( unidade == null ) {
            return null;
        }
        String nomeUnidade = unidade.getNomeUnidade();
        if ( nomeUnidade == null ) {
            return null;
        }
        return nomeUnidade;
    }

    protected Unidade colaboradorListDTOToUnidade(ColaboradorListDTO colaboradorListDTO) {
        if ( colaboradorListDTO == null ) {
            return null;
        }

        Unidade unidade = new Unidade();

        unidade.setNomeUnidade( colaboradorListDTO.getNomeUnidade() );

        return unidade;
    }
}
