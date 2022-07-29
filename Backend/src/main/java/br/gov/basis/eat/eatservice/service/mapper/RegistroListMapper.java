package br.gov.basis.eat.eatservice.service.mapper;

import br.gov.basis.eat.eatservice.domain.Registro;
import br.gov.basis.eat.eatservice.service.dto.RegistroListDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring")
public interface RegistroListMapper extends EntityMapper<RegistroListDTO, Registro>{
    @Override
    @Mapping(source = "prioridade.descricao", target = "nomePrioridade")
    @Mapping(source = "statusRegistro.descricao", target = "nomeStatus")
    @Mapping(source = "cliente.nome", target = "nomeCliente")
    RegistroListDTO toDto(Registro registro);

    @Override
    @InheritInverseConfiguration
    Registro toEntity(RegistroListDTO registroListDTO);

    @AfterMapping
    default void calculoTempoVida(Registro entity, @MappingTarget RegistroListDTO dto){
        dto.setTempoVida(ChronoUnit.DAYS.between(entity.getDataInicio(), LocalDate.now()));
    }
}
