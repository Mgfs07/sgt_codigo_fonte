package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.StatusAcao;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusAcaoRepository extends JpaRepository<StatusAcao,Integer> {

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.DropdownDTO(sa.descricao, sa.id) " +
        "from StatusAcao sa")
    List<DropdownDTO> getDropdown();
}
