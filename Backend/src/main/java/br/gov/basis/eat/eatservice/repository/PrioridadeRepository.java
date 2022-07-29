package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Prioridade;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrioridadeRepository extends JpaRepository<Prioridade, Integer> {

    @Query("SELECT NEW " +
        "br.gov.basis.eat.eatservice.service.dto.DropdownDTO(p.descricao, p.id) " +
        "FROM Prioridade p")
    List<DropdownDTO> getDropdown();
}
