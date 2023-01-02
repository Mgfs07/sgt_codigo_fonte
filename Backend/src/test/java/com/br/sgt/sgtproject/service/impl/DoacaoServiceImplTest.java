package com.br.sgt.sgtproject.service.impl;

import com.br.sgt.sgtproject.builder.DoacaoBuilder;
import com.br.sgt.sgtproject.repository.DoacaoRepository;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;
import com.br.sgt.sgtproject.service.mapper.DoacaoListMapper;
import com.br.sgt.sgtproject.service.mapper.DoacaoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DoacaoServiceImplTest {

    @InjectMocks
    private DoacaoServiceImpl service;

    @Mock
    private DoacaoMapper mapper;

    @Mock
    private DoacaoListMapper listMapper;

    @Mock
    private DoacaoRepository repository;


    @Test
    void buscarTodos_seTiverCerto() {
        when(repository.findAll()).thenReturn(List.of(DoacaoBuilder.umaDoacao().todosAtributo().builder()));
        when(listMapper.toDto(anyList())).thenReturn(List.of(DoacaoBuilder.umaDoacaoList().todosAtributoList().builderList()));
        List<DoacaoListDTO> listaDoacao = service.buscarTodos();
        verify(listMapper, times(1)).toDto(anyList());
        assertNotNull(listaDoacao);
        assertFalse(listaDoacao.isEmpty());
        assertEquals(1, listaDoacao.size());
    }

//    @Test
//    void buscarPorId_seTiverCerto(){
//        when(repository.findById(anyInt())).thenReturn(Optional.of(DoacaoBuilder.umaDoacao().todosAtributo().builder()));
//        Doacao doacao = service
//    }

}
