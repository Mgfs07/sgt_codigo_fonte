package com.br.sgt.sgtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doacoes")
    @SequenceGenerator(name = "seq_doacoes", sequenceName = "seq_doacoes", allocationSize = 1)
    @Column(name = "id_doacao", nullable = false)
    private Integer id;

    @Column(name = "nome_doador", nullable = false)
    private String nomeDoador;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_pagamento", referencedColumnName = "id_pagamento", nullable = false)
    private Pagamento doadoPara;

    @Column(name = "valor_doado", nullable = false)
    private Double valorDoado;

    @Column(name = "data_doacao")
    private LocalDate dataDoacao;

}
