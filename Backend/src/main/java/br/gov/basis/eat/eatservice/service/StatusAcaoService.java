package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.repository.StatusAcaoRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusAcaoService {

    private final StatusAcaoRepository statusAcaoRepository;

    public List<DropdownDTO> listar(){
        return statusAcaoRepository.getDropdown();
    }

}
