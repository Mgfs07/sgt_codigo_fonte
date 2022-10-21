package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.GastoService;
import com.br.sgt.sgtproject.service.dto.GastoDTO;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import com.br.sgt.sgtproject.service.dto.ValoresDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/gastos")
@RequiredArgsConstructor
public class GastoResource {

    private final GastoService service;

    @GetMapping
    public ResponseEntity<List<GastoListDTO>> buscarTodos(){
        List<GastoListDTO> gastosList = service.buscarTodos();
        return new ResponseEntity<>(gastosList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoDTO> buscarPorId(@PathVariable("id") Integer id){
        GastoDTO gastoDTO = service.buscar(id);
        return new ResponseEntity<>(gastoDTO, HttpStatus.OK);
    }

    @GetMapping("/valores")
    public ResponseEntity<ValoresDTO> valores(){
        ValoresDTO valoresDTO = service.valorGasto();
        return new ResponseEntity<>(valoresDTO, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<GastoDTO> salvar(@Valid @RequestBody GastoDTO dto){
        GastoDTO gastoDTO = service.salvar(dto);
        return new ResponseEntity<>(gastoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GastoDTO> atualizar(@Valid @RequestBody GastoDTO dto){
        GastoDTO doacoesDTO = service.salvar(dto);
        return new ResponseEntity<>(doacoesDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable("id") Integer id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
