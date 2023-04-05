package com.br.sgt.sgtproject.web;

import com.br.sgt.sgtproject.builder.GastoBuilder;
import com.br.sgt.sgtproject.builder.PagamentoColaboradorBuilder;
import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.impl.util.TesteUtil;
import com.br.sgt.sgtproject.service.mapper.GastoMapper;
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
public class GastoResourceTest {


    @Autowired
    private GastoBuilder builder;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GastoMapper mapper;

    @Autowired
    private PagamentoColaboradorBuilder pagamentoColaboradorBuilder;


    @Test
    public void buscarGasto_MetodoGet_deveRetornarArray() throws Exception {
        Gasto gasto = builder.contruir();
        mvc.perform(get("/api/gastos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void buscarValoresGastos_MetodoGet_deveRetornarArray() throws Exception {
        PagamentoColaborador pagamentoColaborador = pagamentoColaboradorBuilder.contruir();
        mvc.perform(get("/api/gastos/valores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomePagamento[0]").isNotEmpty())
                .andExpect(jsonPath("$.total").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void buscarGasto_MetodoGet_deveRetornarUmGastoDTO() throws Exception {
        Gasto gasto = builder.contruir();

        mvc.perform(get("/api/gastos/{id}", gasto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(gasto.getId()));

        mvc.perform(get("/api/gastos/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void buscarGasto_MetodoGet_deveRetornarUmaExceptionNaoEncontrado() throws Exception {
        mvc.perform(get("/api/gastos/9999"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void salvarUmGasto_MetodoPost_deveRetornarUmGastoDTO() throws Exception {
        Gasto gasto = builder.construirEntidade();

        mvc.perform(post("/api/gastos")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(gasto)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void EditarUmGasto_MetodoPost_deveRetornarUmGastoDTO() throws Exception {
        Gasto gasto = builder.contruir();

        gasto.setMotivo("THAIS");

        mvc.perform(put("/api/gastos")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(gasto)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(gasto.getId()))
                .andExpect(jsonPath("$.descricao").value(gasto.getDescricao()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deletarGasto_MetodoDelete() throws Exception {
        Gasto gasto = builder.contruir();

        mvc.perform(delete("/api/gastos/{id}", gasto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
