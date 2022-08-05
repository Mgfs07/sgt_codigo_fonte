package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PagamentoColaboradorDTO {

    private Integer id;

    private Integer idColaborador; // mudar

    private Integer idPagamento; // mudar

    private String observacao;

    private Boolean retiradoLugar;

    private Integer pagamentosRetirado; // mduar

    private LocalDate dataPagamento;

    private Double valorPago;
}
