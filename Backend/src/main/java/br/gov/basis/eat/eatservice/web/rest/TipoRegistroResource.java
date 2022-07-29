package br.gov.basis.eat.eatservice.web.rest;

import br.gov.basis.eat.eatservice.service.TipoRegistroService;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/tipos-registro")
@RequiredArgsConstructor
public class TipoRegistroResource {

    private final TipoRegistroService tipoRegistroService;

    @GetMapping
    public ResponseEntity<List<DropdownDTO>> listar(){
        return new ResponseEntity<>(tipoRegistroService.listar(), HttpStatus.OK);
    }

}
