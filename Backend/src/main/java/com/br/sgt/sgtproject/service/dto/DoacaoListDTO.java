package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DoacaoListDTO {

    private Integer id;
    private String nomeDoador;
    private String observacao;
    private String doadoParaPagamento;
    private Double valorDoado;
    private LocalDate dataDoacao;
}
