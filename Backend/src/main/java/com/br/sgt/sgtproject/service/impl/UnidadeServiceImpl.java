package com.br.sgt.sgtproject.service.impl;

import com.br.sgt.sgtproject.repository.UnidadeRepository;
import com.br.sgt.sgtproject.service.UnidadeService;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public List<DropdownDTO> buscarUnidades(){
        return unidadeRepository.unidadeDropdown();
    }
}
