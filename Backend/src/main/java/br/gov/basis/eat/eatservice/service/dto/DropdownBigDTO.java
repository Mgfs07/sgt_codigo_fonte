package br.gov.basis.eat.eatservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DropdownBigDTO implements Serializable {

    private String label;
    private Long value;
    private String icon;

    public DropdownBigDTO(String label, Long value) {
        this.label = label;
        this.value = value;
    }
}
