package br.gov.basis.eat.eatservice.web.rest;

import br.gov.basis.eat.eatservice.service.ItemService;
import br.gov.basis.eat.eatservice.service.dto.DropdownItemDTO;
import br.gov.basis.eat.eatservice.service.dto.ItemDTO;
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
@RequestMapping("/api/itens-registro")
@RequiredArgsConstructor
public class ItemResource {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> buscarTodos(){
        return new ResponseEntity<>(itemService.buscarTodos(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> salvar(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.salvar(itemDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        itemService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> editar(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.salvar(itemDTO), HttpStatus.OK);
    }

    @GetMapping("/registro/{idRegistro}")
    public ResponseEntity<List<ItemDTO>> buscarItensRegistro(@PathVariable("idRegistro") Long id){
        return new ResponseEntity<>(itemService.buscarItensRegistro(id),HttpStatus.OK);
    }

    @GetMapping("/dropdown-item/{idRegistro}")
    public ResponseEntity<DropdownItemDTO> buscarDropdownItem(@PathVariable("idRegistro") Long idRegistro)  {
        return new ResponseEntity<>(itemService.buscarDropdownItem(idRegistro), HttpStatus.OK);
    }
}
