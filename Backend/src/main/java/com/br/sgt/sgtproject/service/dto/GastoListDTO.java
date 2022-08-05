package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GastoListDTO {

    private Integer id;
    private String motivo;
    private String NomeColaborador;
    private LocalDate dataDispesa;
    private Double valorRetirado;
    private String retiradoDoPagamento;

}
