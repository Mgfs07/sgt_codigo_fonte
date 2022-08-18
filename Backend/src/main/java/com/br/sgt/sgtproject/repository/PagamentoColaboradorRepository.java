package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoColaboradorRepository extends JpaRepository<PagamentoColaborador, Integer> {

    List<PagamentoColaborador> findAllByOrderByColaboradorId ();
}
