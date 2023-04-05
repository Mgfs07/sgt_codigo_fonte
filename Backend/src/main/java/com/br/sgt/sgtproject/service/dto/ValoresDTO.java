package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValoresDTO {

    private List<TipoPagamentoDTO> nomePagamento;
    private Double total;
}
