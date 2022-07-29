package br.gov.basis.eat.eatservice.web.rest;

import br.gov.basis.eat.eatservice.domain.Cliente;
import br.gov.basis.eat.eatservice.service.ClienteService;
import br.gov.basis.eat.eatservice.service.dto.ClienteDTO;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
        Date
        return new ResponseEntity<>(clienteService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos() {
        return new ResponseEntity<>(clienteService.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteSalvo = clienteService.inserir(clienteDTO);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizar(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtt = clienteService.atualizar(clienteDTO);
        return new ResponseEntity<>(clienteAtt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<DropdownBigDTO>> buscarDropdowns(){
        return new ResponseEntity<>(clienteService.obterDropdown(),HttpStatus.OK);
    }
}
