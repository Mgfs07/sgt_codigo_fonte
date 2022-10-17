package com.br.sgt.sgtproject.service.mapper;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-11T18:06:10-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class ColaboradorMapperImpl implements ColaboradorMapper {

    @Override
    public Colaborador toEntity(ColaboradorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.setId( dto.getId() );
        colaborador.setNomeColaborador( dto.getNomeColaborador() );
        colaborador.setTelefone( dto.getTelefone() );
        colaborador.setEmail( dto.getEmail() );
        colaborador.setAtivo( dto.getAtivo() );

        return colaborador;
    }

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
    public ColaboradorDTO toDto(Colaborador entity) {
        if ( entity == null ) {
            return null;
        }

        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();

        colaboradorDTO.setId( entity.getId() );
        colaboradorDTO.setNomeColaborador( entity.getNomeColaborador() );
        colaboradorDTO.setTelefone( entity.getTelefone() );
        colaboradorDTO.setEmail( entity.getEmail() );
        colaboradorDTO.setAtivo( entity.getAtivo() );

        return colaboradorDTO;
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
}
