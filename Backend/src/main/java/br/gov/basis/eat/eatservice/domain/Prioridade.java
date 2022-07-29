package br.gov.basis.eat.eatservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "prioridade")

public class Prioridade{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column (name = "descricao", nullable = false, length = 50)
    private String descricao;
}
