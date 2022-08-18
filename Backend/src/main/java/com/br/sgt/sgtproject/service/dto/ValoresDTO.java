package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ValoresDTO implements Serializable {

    private Double mensalidade;
    private Double campori;
    private Double camisa;
    private Double acampEdessa;
    private Double caderno;
    private Double livre;
    private Double doacoes;
    private Double total;
}
