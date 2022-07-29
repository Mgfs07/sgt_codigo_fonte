package br.gov.basis.eat.eatservice.service.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DropdownItemDTO {
    private List<DropdownBigDTO> projeto;
    private List<DropdownBigDTO> usuario;
    private List<DropdownDTO> statusAcao;
}
