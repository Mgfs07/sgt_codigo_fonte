package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagamentoColaboradorListDTO implements Serializable {

    private Integer id;

    private String nomeColaborador;

    private String nomePagamento;

    private LocalDateTime dataPagamento;

    private Double valorPago;
}
