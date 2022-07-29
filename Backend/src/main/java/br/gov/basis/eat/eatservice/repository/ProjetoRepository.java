package br.gov.basis.eat.eatservice.repository;

import br.gov.basis.eat.eatservice.domain.Projeto;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import br.gov.basis.eat.eatservice.service.dto.ProjetoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.ProjetoListDTO(p.id, p.nome, cl.nome, p.ativo ) " +
        "from Projeto p join p.cliente cl")
    List<ProjetoListDTO> listar();

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.ProjetoListDTO(p.id, p.nome, cl.nome, p.ativo) " +
        "from Projeto p join p.cliente cl where p.ativo = :ativo ")
    List<ProjetoListDTO> listarAtivoInativo(@Param("ativo") Boolean ativo);

    @Query("select new " +
        "br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO(p.nome, p.id) " +
        "from Projeto p where p.cliente.id = :idCliente ")
    List<DropdownBigDTO> buscarProjetoDropdown(@Param("idCliente") Long idCliente);

    Optional<Projeto> findByNome(String nome);

}
