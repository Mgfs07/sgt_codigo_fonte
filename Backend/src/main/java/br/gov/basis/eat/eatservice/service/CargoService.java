package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.repository.CargoRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;

    public List<DropdownDTO> listar() {
        return cargoRepository.getDropdown();
    }
}

