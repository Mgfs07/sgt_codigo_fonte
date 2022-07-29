package br.gov.basis.eat.eatservice.web.rest;

import br.gov.basis.eat.eatservice.service.StatusAcaoService;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status-acao")
@RequiredArgsConstructor
public class StatusAcaoResource {

    private final StatusAcaoService statusAcaoService;

    @GetMapping
    public ResponseEntity<List<DropdownDTO>> listar(){
        return new ResponseEntity<>(statusAcaoService.listar(), HttpStatus.OK);
    }
}
