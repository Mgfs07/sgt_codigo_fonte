package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoPagamentoDTO {

    private String nomePagamento;
    private Double valorPago;
}
