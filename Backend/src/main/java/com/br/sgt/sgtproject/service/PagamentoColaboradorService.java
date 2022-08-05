package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.repository.PagamentoColaboradorRepository;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import com.br.sgt.sgtproject.service.dto.ValoresDTO;
import com.br.sgt.sgtproject.service.mapper.PagamentoColaboradorListMapper;
import com.br.sgt.sgtproject.service.mapper.PagamentoColaboradorMapper;
import com.br.sgt.sgtproject.service.util.MensagemPagamentoColaboradorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoColaboradorService {

    private final PagamentoColaboradorRepository repository;
    private final PagamentoColaboradorListMapper listMapper;
    private final PagamentoColaboradorMapper mapper;

    public List<PagamentoColaboradorListDTO> buscarTodos(){
        return listMapper.toDto(repository.findAll());
    }

    private PagamentoColaborador buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                MensagemPagamentoColaboradorUtil.PAGAMENTO_COLABORADOR_NAO_ENCONTRADO));
    }

    public PagamentoColaboradorDTO buscar(Integer id){
        return mapper.toDto(buscarPorId(id));
    }

    public PagamentoColaboradorDTO salvar(PagamentoColaboradorDTO dto){
        PagamentoColaborador pagamentoColaborador = mapper.toEntity(dto);
        return mapper.toDto(repository.save(pagamentoColaborador));
    }

    public ValoresDTO valorDoacaoPagamento(){
        ValoresDTO valoresDTO = new ValoresDTO();
        valoresDTO.setMensalidade(repository.valorDoado(1));
        valoresDTO.setCampori(repository.valorDoado(2));
        valoresDTO.setCamisa(repository.valorDoado(3));
        valoresDTO.setAcampEdessa(repository.valorDoado(4));
        valoresDTO.setCaderno(repository.valorDoado(5));
        valoresDTO.setLivre(repository.valorDoado(6));
        valoresDTO.setDoacoes(repository.valorDoado(7));
        return valoresDTO;
    }


}
