package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Doacao;
import org.apache.lucene.search.grouping.DoubleRangeGroupSelector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

    @Query("select sum(d.valorDoado) from Doacao d where d.doadoPara.id = :idPagamento")
    Double valorDoado(@Param("idPagamento") Integer idPagamento);
}
