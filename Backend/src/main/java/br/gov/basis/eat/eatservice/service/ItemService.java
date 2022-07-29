package br.gov.basis.eat.eatservice.service;

import br.gov.basis.eat.eatservice.domain.Item;
import br.gov.basis.eat.eatservice.repository.ItemRepository;
import br.gov.basis.eat.eatservice.service.dto.DropdownItemDTO;
import br.gov.basis.eat.eatservice.service.dto.ItemDTO;
import br.gov.basis.eat.eatservice.service.mapper.ItemMapper;
import br.gov.basis.eat.eatservice.service.util.MensagemItemUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    private final ProjetoService projetoService;
    private final StatusAcaoService statusAcaoService;

    private final UsuarioService usuarioService;

    public List<ItemDTO> buscarTodos(){
            return itemMapper.toDto(itemRepository.findAll());
    }

    public ItemDTO buscarPorId(Long idItem) {
        return itemMapper.toDto(itemRepository.findById(idItem)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemItemUtil.ITEM_NAO_ENCONTRADO)));
    }

    public ItemDTO salvar(ItemDTO item) {
        return itemMapper.toDto(itemRepository.save(itemMapper.toEntity(item)));
    }

    public void deletar(Long idItem) {
        Item item = itemRepository.findById(idItem)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemItemUtil.ITEM_NAO_ENCONTRADO));
        item.setAtivo(false);
        itemRepository.save(item);
    }

    public List<ItemDTO> buscarItensRegistro(Long idRegistro) {
        return itemMapper.toDto(itemRepository.itensDeUmRegistro(idRegistro));
    }

    public DropdownItemDTO buscarDropdownItem(Long idRegistro) {
        DropdownItemDTO dropdownItemDTO = new DropdownItemDTO();
        dropdownItemDTO.setProjeto(projetoService.obterDropdown(idRegistro));
        dropdownItemDTO.setStatusAcao(statusAcaoService.listar());
        dropdownItemDTO.setUsuario(usuarioService.obterDropdown());
        return dropdownItemDTO;
    }
}
