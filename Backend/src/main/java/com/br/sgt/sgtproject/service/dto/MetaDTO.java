package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class MetaDTO implements Serializable {

    private String nomePagamento;
    private Integer valorPago;
    private Integer quantoFalta;
}
