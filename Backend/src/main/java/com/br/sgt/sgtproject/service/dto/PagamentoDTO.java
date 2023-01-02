package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO implements Serializable {

    private Integer id;
    private String nomePagamento;
    private Double valorMeta;
    private Boolean ativo = true;
}
