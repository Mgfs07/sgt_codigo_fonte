package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Cargo;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    @Query("select new br.gov.basis.eat.eatservice.service.dto.DropdownDTO(c.descricao, c.id)" +
        "from Cargo c")
    List<DropdownDTO> getDropdown();
}
