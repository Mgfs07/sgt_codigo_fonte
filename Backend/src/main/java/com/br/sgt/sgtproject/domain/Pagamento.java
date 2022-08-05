package com.br.sgt.sgtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @Column(name = "id_pagamento", nullable = false)
    private Integer id;

    @Column(name = "nome_pagamento")
    private String nomePagamento;

}
