package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.repository.StatusRegistroRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusRegistroService {

    private final StatusRegistroRepository statusRegistroRepository;

    public List<DropdownDTO> listar(){
        return statusRegistroRepository.getDropdown();
    }
}
