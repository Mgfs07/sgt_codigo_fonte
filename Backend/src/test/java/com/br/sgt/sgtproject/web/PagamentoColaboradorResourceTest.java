package com.br.sgt.sgtproject.web;

import com.br.sgt.sgtproject.builder.PagamentoColaboradorBuilder;
import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.impl.util.TesteUtil;
import com.br.sgt.sgtproject.service.mapper.PagamentoColaboradorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PagamentoColaboradorResourceTest {


    @Autowired
    private PagamentoColaboradorBuilder builder;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PagamentoColaboradorMapper mapper;


    @Test
    public void buscarPagamentoColaborador_MetodoGet_deveRetornarArray() throws Exception {
        PagamentoColaborador pagamentoColaborador = builder.contruir();
        mvc.perform(get("/api/pagamentos-colaboradores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void buscarPagamentoColaborador_MetodoGet_deveRetornarUmPagamentoColaboradorDTO() throws Exception {
        PagamentoColaborador pagamentoColaborador = builder.contruir();

        mvc.perform(get("/api/pagamentos-colaboradores/{id}", pagamentoColaborador.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pagamentoColaborador.getId()));

        mvc.perform(get("/api/pagamentos-colaboradores/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void buscarPagamentoColaborador_MetodoGet_deveRetornarUmaExceptionNaoEncontrado() throws Exception {
        mvc.perform(get("/api/pagamentos-colaboradores/9999"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void salvarUmPagamentoColaborador_MetodoPost_deveRetornarUmPagamentoColaboradorDTO() throws Exception {
        PagamentoColaborador pagamentoColaborador = builder.construirEntidade();

        mvc.perform(post("/api/pagamentos-colaboradores")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(pagamentoColaborador)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void EditarUmPagamentoColaborador_MetodoPost_deveRetornarUmPagamentoColaboradorDTO() throws Exception {
        PagamentoColaborador pagamentoColaborador = builder.contruir();

        pagamentoColaborador.setObservacao("THAIS");

        mvc.perform(put("/api/pagamentos-colaboradores")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(pagamentoColaborador)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pagamentoColaborador.getId()))
                .andExpect(jsonPath("$.observacao").value(pagamentoColaborador.getObservacao()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deletarPagamentoColaborador_MetodoDelete() throws Exception {
        PagamentoColaborador pagamentoColaborador = builder.contruir();

        mvc.perform(delete("/api/pagamentos-colaboradores/{id}", pagamentoColaborador.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
