package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.PagamentoColaboradorService;
import com.br.sgt.sgtproject.service.dto.MetaDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import com.br.sgt.sgtproject.service.dto.PagantesDTO;
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

    @GetMapping("/meta/{id}")
    public ResponseEntity<List<MetaDTO>> enviarMetas(@PathVariable("id") Integer id) {
        List<MetaDTO> metas = service.metas(id);
        return new ResponseEntity<>(metas, HttpStatus.OK);
    }

    @GetMapping("/pagou/{id}")
    public ResponseEntity<List<PagantesDTO>> quemPagou(@PathVariable("id") Integer id) {
        List<PagantesDTO> pagantes = service.quemPagou(id);
        return new ResponseEntity<>(pagantes, HttpStatus.OK);
    }

    @GetMapping("/naopagou/{id}")
    public ResponseEntity<List<String>> naoPagou(@PathVariable("id") Integer id) {
        List<String> pagantes = service.quemNaoPagou(id);
        return new ResponseEntity<>(pagantes, HttpStatus.OK);
    }
}
