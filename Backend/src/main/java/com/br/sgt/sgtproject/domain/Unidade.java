package com.br.sgt.sgtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "unidade")
public class Unidade {

    @Id
    @Column(name = "id_unidade", nullable = false)
    private Integer id;

    @Column(name = "nome_unidade", nullable = false)
    private String nomeUnidade;
}
