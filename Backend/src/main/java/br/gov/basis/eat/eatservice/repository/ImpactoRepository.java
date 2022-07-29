package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Impacto;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpactoRepository extends JpaRepository<Impacto, Integer> {

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.DropdownDTO(i.descricao, i.id)" +
        "from Impacto i")
    List<DropdownDTO> getDropdown();
}
