package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.service.dto.DoacaoDTO;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;

import java.util.List;

public interface DoacaoService {

    List<DoacaoListDTO> buscarTodos();

    DoacaoDTO buscar(Integer id);

    DoacaoDTO salvar(DoacaoDTO dto);

    void deletar(Integer id);
}
