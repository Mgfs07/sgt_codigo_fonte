package br.gov.basis.eat.eatservice.web.rest;

import br.gov.basis.eat.eatservice.service.ProjetoService;
import br.gov.basis.eat.eatservice.service.dto.ProjetoDTO;
import br.gov.basis.eat.eatservice.service.dto.ProjetoListDTO;
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

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoResource {

    private final ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoListDTO>> listar(){
        return new ResponseEntity<>(projetoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<ProjetoListDTO>> listarAtivoInativo(@PathVariable Boolean ativo){
        return new ResponseEntity<>(projetoService.listarAtivoInativo(ativo), HttpStatus.OK);
    }

    @GetMapping("/{idProjeto}")
    public ResponseEntity<ProjetoDTO> buscarPorId(@PathVariable Long idProjeto){
        return new ResponseEntity<>(projetoService.buscarPorId(idProjeto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> salvar(@RequestBody ProjetoDTO projetoDTO){
        return new ResponseEntity<>(projetoService.salvar(projetoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idProjeto}")
    public ResponseEntity<Void> deletar (@PathVariable("idProjeto") Long idProjeto){
        projetoService.deletar(idProjeto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<ProjetoDTO> editar (@RequestBody ProjetoDTO projetoDTO){
        return new ResponseEntity<>(projetoService.salvar(projetoDTO), HttpStatus.OK);
    }
}
