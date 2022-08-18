package com.br.sgt.sgtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "pagamento_colaborador")
public class PagamentoColaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pagamento_colaborador")
    @SequenceGenerator(name = "seq_pagamento_colaborador", sequenceName = "seq_pagamento_colaborador", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id_colaborador")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "pagamentos_id", referencedColumnName = "id_pagamento")
    private Pagamento pagamento;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "retirado_lugar")
    private Boolean retiradoLugar;

    @ManyToOne
    @JoinColumn(name = "pagamentos_retirado_id_pagamento")
    private Pagamento pagamentoRetirado;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @Column(name = "valor_pago", nullable = false)
    private Double valorPago;

}
