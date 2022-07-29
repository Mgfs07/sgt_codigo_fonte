package br.gov.basis.eat.eatservice.service.mapper;

import br.gov.basis.eat.eatservice.domain.Usuario;
import br.gov.basis.eat.eatservice.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
}
