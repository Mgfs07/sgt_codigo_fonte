package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.PagamentoService;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoResource {

    private final PagamentoService service;

    @GetMapping("/select")
    public ResponseEntity<List<DropdownDTO>> buscar() {
        return new ResponseEntity<>(service.buscarPagamentos(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> buscarTodos() {
        List<PagamentoDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> salvar(@Valid @RequestBody PagamentoDTO dto){
        PagamentoDTO pagamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PagamentoDTO> atualizar(@Valid @RequestBody PagamentoDTO dto){
        PagamentoDTO pagamentoDTO = service.salvar(dto);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable("id") Integer id ){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
