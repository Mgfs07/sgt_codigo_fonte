package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.repository.TipoRegistroRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoRegistroService {

    private final TipoRegistroRepository tipoRegistroRepository;

    public List<DropdownDTO> listar(){
        return tipoRegistroRepository.getDropdown();
    }
}
