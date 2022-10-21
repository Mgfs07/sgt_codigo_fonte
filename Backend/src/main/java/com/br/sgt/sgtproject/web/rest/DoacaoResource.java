package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.DoacaoService;
import com.br.sgt.sgtproject.service.dto.DoacaoDTO;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
@RequiredArgsConstructor
public class DoacaoResource {

    private final DoacaoService service;

    @GetMapping
    public ResponseEntity<List<DoacaoListDTO>> buscarTodos(){
        List<DoacaoListDTO> doacaoListDTOS = service.buscarTodos();
        return new ResponseEntity<>(doacaoListDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoacaoDTO> buscarPorId(@PathVariable("id") Integer id){
        DoacaoDTO doacaoDTO = service.buscar(id);
        return new ResponseEntity<>(doacaoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoacaoDTO> salvar(@Valid @RequestBody DoacaoDTO dto){
        DoacaoDTO doacaoDTO = service.salvar(dto);
        return new ResponseEntity<>(doacaoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DoacaoDTO> atualizar(@Valid @RequestBody DoacaoDTO dto){
        DoacaoDTO doacaoDTO = service.salvar(dto);
        return new ResponseEntity<>(doacaoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("{idDoacao}")
    public ResponseEntity<Void> deletar(@PathVariable("idDoacao") Integer idDoacao){
        service.deletar(idDoacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
