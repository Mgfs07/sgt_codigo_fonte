package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.service.dto.DropdownDTO;

import java.util.List;

public interface PagamentoService {

    List<DropdownDTO> buscarPagamentos();
}
