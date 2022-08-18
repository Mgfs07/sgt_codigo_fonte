package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagamentoColaboradorDTO implements Serializable {

    private Integer id;

    private Integer idColaborador;

    private Integer idPagamento;

    private String observacao;

    private Boolean retiradoLugar;

    private Integer pagamentosRetirado; // mduar

    private LocalDateTime dataPagamento;

    private Double valorPago;
}
