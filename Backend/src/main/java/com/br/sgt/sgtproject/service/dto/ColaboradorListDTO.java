package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ColaboradorListDTO implements Serializable {

    private Integer id;
    private String nomeColaborador;
    private String nomeUnidade;
    private String telefone;

}
