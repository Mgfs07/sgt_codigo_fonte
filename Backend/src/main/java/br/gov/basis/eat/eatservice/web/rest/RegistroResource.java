package br.gov.basis.eat.eatservice.web.rest;


import br.gov.basis.eat.eatservice.service.RegistroService;
import br.gov.basis.eat.eatservice.service.dto.DropdownRegistroDTO;
import br.gov.basis.eat.eatservice.service.dto.RegistroDTO;
import br.gov.basis.eat.eatservice.service.dto.RegistroListDTO;
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
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class RegistroResource {
    private final RegistroService registroService;

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDTO> buscar(@PathVariable Long id){
        return new ResponseEntity<>(registroService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/dropdown-registro")
    public ResponseEntity<DropdownRegistroDTO> buscarDropdownRegistro(){
        return new ResponseEntity<>(registroService.buscarDropdownRegistro(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RegistroListDTO>> buscarTodas(){
        return new ResponseEntity<>(registroService.buscarTodas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegistroDTO> inserir(@Valid @RequestBody RegistroDTO registroDTO){
        RegistroDTO registroSalvo = registroService.inserir(registroDTO);
        return new ResponseEntity<>(registroSalvo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RegistroDTO> atualizar(@Valid @RequestBody RegistroDTO registroDTO){
        RegistroDTO registroAtt= registroService.atualizar(registroDTO);
        return new ResponseEntity<>(registroAtt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        registroService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
