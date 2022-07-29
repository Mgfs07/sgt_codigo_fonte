package br.gov.basis.eat.eatservice.service.mapper;
import br.gov.basis.eat.eatservice.domain.Usuario;
import br.gov.basis.eat.eatservice.service.dto.UsuarioListDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioListMapper extends EntityMapper<UsuarioListDTO, Usuario>{
    @Override
    @Mapping(source = "cargo.descricao", target = "cargo")
    UsuarioListDTO toDto(Usuario usuario);

    @Override
    @InheritInverseConfiguration
    Usuario toEntity(UsuarioListDTO usuarioListDTO);

}
