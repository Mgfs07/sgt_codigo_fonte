package br.gov.basis.eat.eatservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class RegistroDTO implements Serializable {
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataLimite;

    @NotNull
    private Integer idTipoRegistro;

    @NotNull
    private Integer idImpacto;

    @NotNull
    private Integer idStatusRegistro;

    @NotNull
    private Integer idPrioridade;

    @NotNull
    private Long idCliente;

    private Boolean ativo = true;


}
