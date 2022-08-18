package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class GastoListDTO implements Serializable {

    private Integer id;
    private String motivo;
    private String nomeColaborador;
    private LocalDate dataDispesa;
    private Double valorRetirado;
    private String retiradoDoPagamento;

}
