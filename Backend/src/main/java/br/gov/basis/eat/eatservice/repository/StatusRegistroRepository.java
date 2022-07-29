package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.StatusRegistro;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRegistroRepository extends JpaRepository<StatusRegistro, Integer> {

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.DropdownDTO(so.descricao, so.id) " +
        "from StatusRegistro so")
    List<DropdownDTO> getDropdown();

}
