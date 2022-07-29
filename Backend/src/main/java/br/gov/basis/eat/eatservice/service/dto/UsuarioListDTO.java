package br.gov.basis.eat.eatservice.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioListDTO implements Serializable {

    private Long id;

    private String nome;

    private String sobrenome;

    private String descricao;

    private String login;

    private String cargo;
}
