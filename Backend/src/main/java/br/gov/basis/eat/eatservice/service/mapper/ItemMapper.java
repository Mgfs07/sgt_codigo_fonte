package br.gov.basis.eat.eatservice.service.mapper;

import br.gov.basis.eat.eatservice.domain.Item;
import br.gov.basis.eat.eatservice.service.dto.ItemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Override
    @Mapping(source = "registro.id", target = "idRegistro")
    @Mapping(source = "projeto.id", target = "idProjeto")
    @Mapping(source = "projeto.nome", target ="nomeProjeto")
    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "usuario.nome", target = "nomeUsuario")
    @Mapping(source = "usuario.sobrenome", target = "sobrenomeUsuario")
    @Mapping(source = "statusAcao.id", target = "idStatusAcao")
    @Mapping(source = "statusAcao.descricao", target = "descricaoStatusAcao")
    ItemDTO toDto(Item item);

    @Override
    @InheritInverseConfiguration
    Item toEntity(ItemDTO itemDTO);

}
