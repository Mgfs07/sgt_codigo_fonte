package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class DoacaoDTO implements Serializable {

    private Integer id;
    private String nomeDoador;
    private String observacao;
    private Integer doadoParaPagamento;
    private Double valorDoado;
    private LocalDate dataDoacao;

}
