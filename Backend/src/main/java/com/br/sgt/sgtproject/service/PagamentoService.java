package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.repository.PagamentoRepository;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoService {

    private final PagamentoRepository repository;

    public List<DropdownDTO> buscarPagamentos(){
        return repository.pagamentosDropdown();
    }
}
