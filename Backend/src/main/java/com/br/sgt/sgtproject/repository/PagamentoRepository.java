package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    @Query("select new com.br.sgt.sgtproject.service.dto.DropdownDTO(p.nomePagamento, p.id) " +
            "from Pagamento p where p.ativo = true")
    List<DropdownDTO> pagamentosDropdown();

    @Query("select new com.br.sgt.sgtproject.service.dto.PagamentoDTO(p.id, p.nomePagamento, p.valorMeta, p.ativo) " +
            "from Pagamento p order by p.ativo DESC")
    List<PagamentoDTO> buscarTodos();
}
