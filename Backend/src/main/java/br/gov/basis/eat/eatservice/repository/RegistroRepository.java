package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    Optional<Registro> findByTitulo (String titulo);

    @Query("select o from Registro o where o.ativo = true")
    List<Registro> buscarRegistrosAtivos();
}
