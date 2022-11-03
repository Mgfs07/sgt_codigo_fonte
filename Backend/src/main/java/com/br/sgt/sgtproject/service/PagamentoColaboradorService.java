package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;

import java.util.List;

public interface PagamentoColaboradorService {

    List<PagamentoColaboradorListDTO> buscarTodos();

    PagamentoColaboradorDTO buscar(Integer id);

    PagamentoColaboradorDTO salvar(PagamentoColaboradorDTO dto);

    void deletar (Integer id);
}
