package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.PagamentoColaboradorService;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pagamentos-colaboradores")
@RequiredArgsConstructor
public class PagamentoColaboradorResource {

    private final PagamentoColaboradorService service;

    @GetMapping
    public ResponseEntity<List<PagamentoColaboradorListDTO>> buscarTodos(){
        List<PagamentoColaboradorListDTO> pagamentoColaboradorList = service.buscarTodos();
        return new ResponseEntity<>(pagamentoColaboradorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoColaboradorDTO> buscarPorId(@PathVariable("id") Integer id){
        PagamentoColaboradorDTO pagamentoColaborador = service.buscar(id);
        return new ResponseEntity<>(pagamentoColaborador, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagamentoColaboradorDTO> salvar(@Valid @RequestBody PagamentoColaboradorDTO dto){
        PagamentoColaboradorDTO pagamentoColaborador = service.salvar(dto);
        return new ResponseEntity<>(pagamentoColaborador, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PagamentoColaboradorDTO> atualizar(@Valid @RequestBody PagamentoColaboradorDTO dto){
        PagamentoColaboradorDTO pagamentoColaborador = service.salvar(dto);
        return new ResponseEntity<>(pagamentoColaborador, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable("id") Integer id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
