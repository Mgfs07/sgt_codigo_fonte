package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    
    List<Colaborador> findAllByAtivoIsTrue();

    @Query("select new com.br.sgt.sgtproject.service.dto.DropdownDTO(c.nomeColaborador, c.id) " +
            "from Colaborador c")
    List<DropdownDTO> dropdownColaborador();



}
