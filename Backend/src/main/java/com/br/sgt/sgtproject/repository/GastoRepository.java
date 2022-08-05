package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

    @Query("select sum(g.valorRetirado) from Gasto g where g.retiradoDe.id = :idPagamento")
    Double valorGasto(@Param("idPagamento") Integer idPagamento);

}
