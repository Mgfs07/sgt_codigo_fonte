package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmailGastoDTO implements Serializable {

    private String nomeSolicitador;
    private String titulo;
    private String descricao;
    private Double valorGasto;
    private LocalDate dataGasto;
    private String retiradoPagamento;
}
