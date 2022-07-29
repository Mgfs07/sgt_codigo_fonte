package br.gov.basis.eat.eatservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DropdownDTO implements Serializable {

    private String label;
    private Integer value;

}
