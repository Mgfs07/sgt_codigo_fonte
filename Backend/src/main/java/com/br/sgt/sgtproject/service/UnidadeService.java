package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.repository.UnidadeRepository;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public List<DropdownDTO> buscarUnidades(){
        return unidadeRepository.unidadeDropdown();
    }
}
