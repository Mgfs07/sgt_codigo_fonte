package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select i from Item i where i.registro.id = :idRegistro")
    List<Item> itensDeUmRegistro(@Param("idRegistro") Long idRegistro);
}
