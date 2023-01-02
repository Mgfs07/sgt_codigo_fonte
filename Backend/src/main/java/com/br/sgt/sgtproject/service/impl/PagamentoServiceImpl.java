package com.br.sgt.sgtproject.service.impl;

import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.repository.PagamentoRepository;
import com.br.sgt.sgtproject.service.PagamentoService;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoDTO;
import com.br.sgt.sgtproject.service.mapper.PagamentoMapper;
import com.br.sgt.sgtproject.service.util.MensagemPagamentoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;
    private final PagamentoMapper mapper;

    public List<DropdownDTO> buscarPagamentos(){
        return repository.pagamentosDropdown();
    }

    public List<PagamentoDTO> buscarTodos() {
        return repository. buscarTodos();
    }

    public PagamentoDTO buscar(Integer id) {
        return mapper.toDto(buscarPorId(id));
    }

    private Pagamento buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, MensagemPagamentoUtil.PAGAMENTO_NAO_ENCONTRADO));
    }

    public PagamentoDTO salvar(PagamentoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void deletar(Integer id) {
        Pagamento pagamento = buscarPorId(id);
        pagamento.setAtivo(false);
        repository.save(pagamento);
    }
}
