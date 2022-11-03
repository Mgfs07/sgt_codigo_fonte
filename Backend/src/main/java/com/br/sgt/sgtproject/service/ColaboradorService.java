package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.service.dto.ColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
import com.br.sgt.sgtproject.service.dto.DropdownDTO;

import java.util.List;

public interface ColaboradorService {

    List<ColaboradorListDTO> buscarTodos();

    ColaboradorDTO buscar(Integer id);

    ColaboradorDTO salvar(ColaboradorDTO dto);

    void deletar(Integer id);

    List<DropdownDTO> dropdonwColaborador();
}
