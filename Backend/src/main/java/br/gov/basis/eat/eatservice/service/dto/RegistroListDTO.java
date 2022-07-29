package br.gov.basis.eat.eatservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class RegistroListDTO implements Serializable {
    private Integer id;
    private String idNegociavel;
    private String nomePrioridade;
    private String titulo;
    private String nomeCliente;
    private Date dataLimite;
    private Long tempoVida;
    private String nomeStatus;
    private Boolean ativo = true;
}
