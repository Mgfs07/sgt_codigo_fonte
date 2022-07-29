package br.gov.basis.eat.eatservice.service;


import br.gov.basis.eat.eatservice.repository.PrioridadeRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PrioridadeService {

    private final PrioridadeRepository prioridadeRepository;

    public List<DropdownDTO> listar(){
        return prioridadeRepository.getDropdown();
    }

}
