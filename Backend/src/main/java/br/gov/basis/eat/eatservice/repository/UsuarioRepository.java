package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Usuario;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "select new br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO(concat(u.nome, ' ' , u.sobrenome), u.id) from Usuario u")
    List<DropdownBigDTO> buscarUsuarioDropdown();
}
