package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.TipoRegistro;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoRegistroRepository extends JpaRepository<TipoRegistro, Integer> {

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.DropdownDTO(to.descricao, to.id) " +
        "from TipoRegistro to")
    List<DropdownDTO> getDropdown();
}
