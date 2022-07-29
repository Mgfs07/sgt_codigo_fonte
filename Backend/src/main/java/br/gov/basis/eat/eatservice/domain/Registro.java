package br.gov.basis.eat.eatservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "registro")
@Getter
@Setter
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_registro")
    @SequenceGenerator(name = "seq_registro", sequenceName = "seq_registro", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_negociavel", nullable = false)
    private String idNegociavel;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_limite", nullable = false)
    private LocalDate dataLimite;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_registro", referencedColumnName = "id", nullable = false)
    private TipoRegistro tipoRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_impacto", referencedColumnName = "id", nullable = false)
    private Impacto impacto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_registro", referencedColumnName = "id", nullable = false)
    private StatusRegistro statusRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prioridade", referencedColumnName = "id", nullable = false)
    private Prioridade prioridade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private Cliente cliente;
}
