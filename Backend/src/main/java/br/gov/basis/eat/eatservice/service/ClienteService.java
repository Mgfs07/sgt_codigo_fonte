package br.gov.basis.eat.eatservice.service;


import br.gov.basis.eat.eatservice.domain.Cliente;
import br.gov.basis.eat.eatservice.repository.ClienteRepository;
import br.gov.basis.eat.eatservice.service.dto.ClienteDTO;
import br.gov.basis.eat.eatservice.service.dto.DropdownBigDTO;
import br.gov.basis.eat.eatservice.service.dto.DropdownDTO;
import br.gov.basis.eat.eatservice.service.mapper.ClienteMapper;
import br.gov.basis.eat.eatservice.service.util.MensagemClienteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteDTO> buscarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDto(clientes);
    }

    public ClienteDTO buscarPorId(Long id) {
        return clienteMapper.toDto(clienteRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, MensagemClienteUtil.CLIENTE_NAO_ENCONTRADO )));
    }

    public ClienteDTO inserir(ClienteDTO clienteDTO) {
        clienteRepository.findByNome(clienteDTO.getNome())
            .ifPresent(cliente -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, MensagemClienteUtil.CLIENTE_JA_CADASTRADO);
            });
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    public ClienteDTO atualizar(ClienteDTO clienteDTO) {
        if (Objects.nonNull(clienteDTO.getId())) {
            Cliente cliente = clienteMapper.toEntity(clienteDTO);
            return clienteMapper.toDto(clienteRepository.save(cliente));
        }
        return clienteDTO;
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MensagemClienteUtil.DELETAR_ERRO));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    public List<DropdownBigDTO> obterDropdown(){
        return clienteRepository.buscarClienteDropdown();
    }
}
