package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

    Optional<Colaborador> findByNomeColaborador(String nome);

    @Query("select new com.br.sgt.sgtproject.service.dto.DropdownDTO(c.nomeColaborador, c.id) " +
            "from Colaborador c")
    List<DropdownDTO> dropdownColaborador();

//    @Query("select new com.br.sgt.sgtproject.service.dto.MetaDTO(p.nomePagamento, " +
//            "case when sum(pc.valorPago) > 0 then sum(pc.valorPago) else 0 end, " +
//            "case when (p.valorMeta - sum(pc.valorPago)) > 0 then (p.valorMeta - sum(pc.valorPago)) else 0 end) " +
//            "from Pagamento p left join PagamentoColaborador pc on p.id = pc.pagamento.id and pc.colaborador.id = :id " +
//            "group by p.id")
//    List<MetaDTO> buscarGastosColaborador(@Param("id") Integer id);
}
