package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.service.dto.DropdownDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoDTO;

import java.util.List;

public interface PagamentoService {

    List<DropdownDTO> buscarPagamentos();

    List<PagamentoDTO> buscarTodos();

    PagamentoDTO buscar(Integer id);

    PagamentoDTO salvar(PagamentoDTO dto);
    void deletar(Integer id);
}
