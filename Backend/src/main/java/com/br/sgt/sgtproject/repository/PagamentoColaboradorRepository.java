package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.MetaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoColaboradorRepository extends JpaRepository<PagamentoColaborador, Integer> {

    List<PagamentoColaborador> findAllByOrderByColaboradorId ();

    @Query("select " +
            "p.nomePagamento as nomePagamento, " +
            "case when sum(pc.valorPago) > 0 then sum(pc.valorPago) else 0 end as valorPago, " +
            "case when (p.valorMeta - sum(pc.valorPago)) > 0 then (p.valorMeta - sum(pc.valorPago)) else 0 end as quantoFalta " +
            "from Pagamento p " +
            "left join PagamentoColaborador pc on p.id = pc.pagamento.id and pc.colaborador.id = :id group by p.id")
    List<MetaDTO> buscarPagamentosColaborador(@Param("id") Integer id);



//--Todo mundo que pagou determinado Pagamento e a quantidade
//            select
//    c.nome_colaborador, sum(pc.valor_pago)
//    from colaborador c
//    join pagamento_colaborador pc on pc.colaborador_id = c.id_colaborador
//    join pagamentos p on p.id_pagamento = pc.pagamentos_id where p.id_pagamento = 1 group by c.nome_colaborador;
//
//
//
//--Todo mundo que nao pagou aquele Pagamento
//    select c.nome_colaborador
//    from colaborador c
//    where c.id_colaborador not in (
//            select pc2.colaborador_id  from pagamento_colaborador pc2 where pc2.pagamentos_id = 2);


}
