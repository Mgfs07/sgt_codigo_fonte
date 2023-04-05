package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmailPagamentoColaboradorDTO implements Serializable {

    private String nomeColaborador;
    private String email;
    private String nomePagamento;
    private Double valorPagamento;
    private LocalDate dataPagamento;
}
