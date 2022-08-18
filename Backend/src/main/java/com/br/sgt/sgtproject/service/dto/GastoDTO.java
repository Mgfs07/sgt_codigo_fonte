package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class GastoDTO implements Serializable {

    private Integer id;

    private String motivo;

    private String descricao;

    private Integer idColaborador; // mudar

    private LocalDate dataDispesa;

    private Double valorRetirado;

    private String comprovante;

    private Integer retiradoDoPagamento;
}
