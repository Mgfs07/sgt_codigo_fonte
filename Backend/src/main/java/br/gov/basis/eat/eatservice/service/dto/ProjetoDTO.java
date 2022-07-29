package br.gov.basis.eat.eatservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO implements Serializable {

    private Long id;
    private String nome;
    private String descricao;
    private Long idCliente;
    private Boolean ativo = true;

}
