//package com.br.sgt.sgtproject.service.impl;
//
//import com.br.sgt.sgtproject.builder.ColaboradorBuilder;
//import com.br.sgt.sgtproject.repository.ColaboradorRepository;
//import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
//import com.br.sgt.sgtproject.service.mapper.ColaboradorListMapper;
//import com.br.sgt.sgtproject.service.mapper.ColaboradorMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//class ColaboradorServiceImplTest {
//
//    @InjectMocks
//    private ColaboradorServiceImpl service;
//
//    @Mock
//    private ColaboradorMapper mapper;
//
//    @Mock
//    private ColaboradorListMapper listMapper;
//
//    @Mock
//    private ColaboradorRepository repository;
//
//    @Test
//    void buscarTodos_deveRetornarTodos_SeTiverTudoCerto() {
//        when(repository.findAll()).thenReturn(List.of(ColaboradorBuilder.umColaborador().comTodoAtributosUmColaborador().buildColaborador()));
//        when(listMapper.toDto(anyList())).thenReturn(List.of(ColaboradorBuilder.umColaboradorList().comTodoAtributosUmColaboradorList().buildColaboradorListDTO()));
//        List<ColaboradorListDTO> listaColaboradores = service.buscarTodos();
//        verify(listMapper, times(1)).toDto(anyList());
//        assertNotNull(listaColaboradores);
//        assertFalse(listaColaboradores.isEmpty());
//        assertEquals( 1, listaColaboradores.size());
//    }
//
//}