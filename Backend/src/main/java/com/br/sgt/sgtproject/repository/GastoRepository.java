package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.service.dto.EmailGastoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

    @Query("select case when sum(pc.valorPago) is null then 0 else sum(pc.valorPago) end from PagamentoColaborador pc where pc.pagamento.id = :idPagamento and pc.retiradoLugar = false")
    Double valorPago(@Param("idPagamento") Integer idPagamento);

    @Query("select case when sum(pc.valorPago) is null then 0 else sum(pc.valorPago) end from PagamentoColaborador pc where pc.pagamentoRetirado.id = :idPagamento and pc.retiradoLugar = true")
    Double valorGastoDoPagamento(@Param("idPagamento") Integer idPagamento);

    @Query("select case when sum(d.valorDoado) is null then 0 else sum(d.valorDoado) end from Doacao d where d.doadoPara.id = :idPagamento")
    Double valorDoado(@Param("idPagamento") Integer idPagamento);

    @Query("select case when sum(g.valorRetirado) is null then 0 else sum(g.valorRetirado) end from Gasto g where g.retiradoDe.id = :idPagamento")
    Double valorGasto(@Param("idPagamento") Integer idPagamento);

    List<Gasto> findAllByOrderByColaboradorId();

    @Query("select new com.br.sgt.sgtproject.service.dto.EmailGastoDTO(g.colaborador.nomeColaborador, " +
            "g.motivo, g.descricao, g.valorRetirado, g.dataDispesa, g.retiradoDe.nomePagamento) " +
            "from Gasto g where g.id = :idGasto")
    EmailGastoDTO buscarGastoClube(@Param("idGasto") Integer idGasto);
}
