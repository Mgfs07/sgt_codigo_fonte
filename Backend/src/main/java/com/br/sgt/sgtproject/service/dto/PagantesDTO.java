package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PagantesDTO {

    private String nomePagamento;
    private Double quantoPagou;
    private LocalDate dataPagamento;
}
