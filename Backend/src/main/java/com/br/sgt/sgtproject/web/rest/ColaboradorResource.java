package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.ColaboradorService;
import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
@RequiredArgsConstructor
public class ColaboradorResource {

    private final ColaboradorService service;

    @GetMapping
    public ResponseEntity<List<ColaboradorListDTO>> buscarTodos(){
        List<ColaboradorListDTO> colaborador = service.buscarTodos();
        return new ResponseEntity<>(colaborador, HttpStatus.OK);
    }

    @GetMapping("/{idColaborador}")
    public ResponseEntity<ColaboradorDTO> buscarPorId(@PathVariable("idColaborador") Integer idColaborador){
        ColaboradorDTO colaboradorDTO = service.buscar(idColaborador);
        return new ResponseEntity<>(colaboradorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColaboradorDTO> salvar(@Valid @RequestBody ColaboradorDTO dto){
        ColaboradorDTO colaboradorDTO = service.salvar(dto);
        return new ResponseEntity<>(colaboradorDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ColaboradorDTO> atualizar(@Valid @RequestBody ColaboradorDTO dto){
        ColaboradorDTO colaboradorDTO = service.salvar(dto);
        return new ResponseEntity<>(colaboradorDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/dropdown")
    public ResponseEntity<List<DropdownDTO>> dropdown(){
        List<DropdownDTO> dropdownDTOS = service.dropdonwColaborador();
        return new ResponseEntity<>(dropdownDTOS, HttpStatus.OK);
    }

}
