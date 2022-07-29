package br.gov.basis.eat.eatservice.web.rest;

import br.gov.basis.eat.eatservice.service.ImpactoService;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/impactos")
@RestController
@RequiredArgsConstructor
public class ImpactoResource {

    private final ImpactoService impactoService;

    @GetMapping
    public ResponseEntity<List<DropdownDTO>> listar() {
        return new ResponseEntity<>(impactoService.listar(), HttpStatus.OK);
    }
}
