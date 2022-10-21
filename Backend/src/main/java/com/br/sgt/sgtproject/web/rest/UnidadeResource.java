package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.UnidadeService;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/unidade")
@RequiredArgsConstructor
public class UnidadeResource {

    private final UnidadeService service;

    @GetMapping("/select")
    public ResponseEntity<List<DropdownDTO>> buscar() {
        return new ResponseEntity<>(service.buscarUnidades(), HttpStatus.OK);
    }
}
