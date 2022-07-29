package br.gov.basis.eat.eatservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ItemListDTO implements Serializable {

    private Long id;
    private Long idRegistro;
    private Long idProjeto;
    private String nomeProjeto;
    private Long idUsuario;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private Long idStatusAcao;
    private String descricaoStatusAcao;
    private String acao;
    private Boolean ativo = true;

}
