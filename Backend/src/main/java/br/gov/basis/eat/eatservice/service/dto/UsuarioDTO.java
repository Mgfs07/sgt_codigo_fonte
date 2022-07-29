package br.gov.basis.eat.eatservice.service.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String login;

    @NotNull
    private Boolean ativo;

    @NotNull
    private Integer idCargo;
}
