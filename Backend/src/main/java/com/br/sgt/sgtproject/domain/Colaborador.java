package com.br.sgt.sgtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_colaborador")
    @SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador", allocationSize = 1)
    @Column(name = "id_colaborador", nullable = false)
    private Integer id;

    @Column(name = "nome_colaborador", nullable = false)
    private String nomeColaborador;

    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false, referencedColumnName = "id_unidade")
    private Unidade unidade;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "ativo")
    private Boolean ativo;

}
