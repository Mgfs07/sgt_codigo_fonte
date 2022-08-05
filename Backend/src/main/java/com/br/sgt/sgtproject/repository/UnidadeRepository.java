package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Unidade;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

    @Query("select new com.br.sgt.sgtproject.service.dto.DropdownDTO(u.nomeUnidade, u.id) " +
            "from Unidade u")
    List<DropdownDTO> unidadeDropdown();
}
