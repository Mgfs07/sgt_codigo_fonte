package com.br.sgt.sgtproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ColaboradorDTO implements Serializable {

    private Integer id;
    private String nomeColaborador;
    private Integer idUnidade;
    private String telefone;
    private String email;
    private Boolean ativo = true;
}
