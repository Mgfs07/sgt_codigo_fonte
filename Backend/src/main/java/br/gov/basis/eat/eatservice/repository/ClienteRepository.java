package br.gov.basis.eat.eatservice.repository;
import br.gov.basis.eat.eatservice.domain.Cliente;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNome(String nome);

    @Query(value = "select new br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO(c.nome, c.id, c.icone ) from Cliente c")
    List<DropdownBigDTO> buscarClienteDropdown();
}
