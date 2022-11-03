package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.service.dto.GastoDTO;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import com.br.sgt.sgtproject.service.dto.ValoresDTO;

import java.util.List;

public interface GastoService {

    List<GastoListDTO> buscarTodos();

    GastoDTO buscar(Integer id);

    GastoDTO salvar(GastoDTO dto);

    ValoresDTO valorGasto();

    void deletar(Integer id);
}
