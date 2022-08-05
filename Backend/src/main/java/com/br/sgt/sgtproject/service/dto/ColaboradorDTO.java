package com.br.sgt.sgtproject.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorDTO {

    private Integer id;
    private String nomeColaborador;
    private Integer idUnidade;
    private String telefone;
    private String email;
    private Boolean ativo = true;
}
