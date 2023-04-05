package com.br.sgt.sgtproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pagamentos")
    @SequenceGenerator(name = "seq_pagamentos", sequenceName = "seq_pagamentos", initialValue = 8)
    @Column(name = "id_pagamento", nullable = false)
    private Integer id;

    @Column(name = "nome_pagamento")
    private String nomePagamento;

    @Column(name = "valor_meta")
    private Double valorMeta;

    @Column(name = "ativo")
    private Boolean ativo;

}
