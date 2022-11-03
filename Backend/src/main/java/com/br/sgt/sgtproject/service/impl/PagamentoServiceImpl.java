package com.br.sgt.sgtproject.service.impl;

import com.br.sgt.sgtproject.repository.PagamentoRepository;
import com.br.sgt.sgtproject.service.PagamentoService;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;

    public List<DropdownDTO> buscarPagamentos(){
        return repository.pagamentosDropdown();
    }
}
