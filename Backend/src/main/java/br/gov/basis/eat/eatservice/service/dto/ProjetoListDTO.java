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
public class ProjetoListDTO implements Serializable {

    private Long id;
    private String nome;
    private String nomeCliente;
    private Boolean ativo = true;

}
