package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoColaboradorRepository extends JpaRepository<PagamentoColaborador, Integer> {

    @Query("select sum(pc.valorPago) from PagamentoColaborador pc where pc.retiradoLugar = true and " +
            "pc.pagamentoRetirado = :idPagamento")
    Double valorDoado(@Param("idPagamento") Integer idPagamento);

}
