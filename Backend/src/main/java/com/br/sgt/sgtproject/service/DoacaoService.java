package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.repository.DoacaoRepository;
import com.br.sgt.sgtproject.service.dto.DoacaoDTO;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;
import com.br.sgt.sgtproject.service.mapper.DoacaoListMapper;
import com.br.sgt.sgtproject.service.mapper.DoacaoMapper;
import com.br.sgt.sgtproject.service.util.MensagemDoacoesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoacaoService {

    private final DoacaoRepository repository;
    private final DoacaoListMapper listMapper;
    private final DoacaoMapper mapper;

    public List<DoacaoListDTO> buscarTodos(){
        return listMapper.toDto(repository.findAll());
    }

    private Doacao buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                MensagemDoacoesUtil.DOACAO_NAO_ENCONTRADA));
    }

    public DoacaoDTO buscar(Integer id){
        return mapper.toDto(buscarPorId(id));
    }

    public DoacaoDTO salvar(DoacaoDTO dto){
        Doacao doacao = mapper.toEntity(dto);
        return mapper.toDto(repository.save(doacao));
    }
}
