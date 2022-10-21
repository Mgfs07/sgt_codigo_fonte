package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.repository.PagamentoColaboradorRepository;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
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
        return listMapper.toDto(repository.findAllByOrderByColaboradorId());
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

    public void deletar (Integer id){
        repository.deleteById(id);
    }

}
