package com.br.sgt.sgtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gastos")
    @SequenceGenerator(name = "seq_gastos", sequenceName = "seq_gastos", allocationSize = 1)
    @Column(name = "id_gastos", nullable = false)
    private Integer id;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador;

    @Column(name = "data_dispesa")
    private LocalDate dataDispesa;

    @Column(name = "valor_retirado", nullable = false)
    private Double valorRetirado;

    @Column(name = "comprovante")
    private String comprovante;

    @ManyToOne
    @JoinColumn(name = "id_pagamento", referencedColumnName = "id_pagamento", nullable = false)
    private Pagamento retiradoDe;

}
