package br.gov.basis.eat.eatservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DropdownRegistroDTO {
    private List<DropdownDTO> impacto;
    private List<DropdownDTO>  tipoRegistro;
    private List<DropdownDTO>  statusRegistro;
    private List<DropdownDTO> prioridade;
    private List<DropdownBigDTO> cliente;
}
