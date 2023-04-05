package com.br.sgt.sgtproject.repository;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.EmailPagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.MetaDTO;
import com.br.sgt.sgtproject.service.dto.PagantesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoColaboradorRepository extends JpaRepository<PagamentoColaborador, Integer> {

    List<PagamentoColaborador> findAllByOrderByColaboradorId ();

    @Query("select new com.br.sgt.sgtproject.service.dto.MetaDTO(" +
            "p.nomePagamento, " +
            "case when sum(pc.valorPago) > 0  then sum(pc.valorPago) else 0 end, " +
            "case when (p.valorMeta - sum(pc.valorPago)) > 0 then (p.valorMeta - sum(pc.valorPago)) else 0 end) " +
            "from Pagamento p " +
            "left join PagamentoColaborador pc on p.id = pc.pagamento.id and pc.colaborador.id = :id group by p.id")
    List<MetaDTO> buscarPagamentosColaborador(@Param("id") Integer id);


    @Query("select new com.br.sgt.sgtproject.service.dto.EmailPagamentoColaboradorDTO" +
            "(pc.colaborador.nomeColaborador, pc.colaborador.email, pc.pagamento.nomePagamento, pc.valorPago, pc.dataPagamento) " +
            "from PagamentoColaborador pc where pc.id = :idPagamentoColaborador")
    EmailPagamentoColaboradorDTO buscarPagamentoColaborador(@Param("idPagamentoColaborador") Integer idPagamentoColaborador);

    @Query("select new com.br.sgt.sgtproject.service.dto.PagantesDTO(c.nomeColaborador, sum(pc.valorPago), " +
            "pc.dataPagamento) " +
            "from Colaborador c " +
            "join PagamentoColaborador pc on pc.colaborador.id = c.id " +
            "join Pagamento p on p.id = pc.pagamento.id where p.id = :idPagamento " +
            "group by c.nomeColaborador, pc.dataPagamento order by c.nomeColaborador")
    List<PagantesDTO> pagantes(@Param("idPagamento") Integer idPagamento);

    @Query("select c.nomeColaborador from Colaborador c where c.id not in " +
            "(select pc.colaborador.id from PagamentoColaborador pc " +
            "where pc.pagamento.id = :idPagamento) and c.ativo = true")
    List<String> quemNaoPagou(@Param("idPagamento") Integer idPagamento);

}
