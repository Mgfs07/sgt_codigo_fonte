package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.repository.ImpactoRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ImpactoService {

    private final ImpactoRepository impactoRepository;

    public List<DropdownDTO> listar() {
        return impactoRepository.getDropdown();
    }
}

