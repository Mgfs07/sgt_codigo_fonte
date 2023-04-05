package com.br.sgt.sgtproject.web;

import com.br.sgt.sgtproject.builder.DoacaoBuilder;
import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.service.impl.util.TesteUtil;
import com.br.sgt.sgtproject.service.mapper.DoacaoMapper;
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
public class DoacaoResourceTest {

    @Autowired
    private DoacaoBuilder builder;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DoacaoMapper mapper;


    @Test
    public void buscarDoacoes_MetodoGet_deveRetornarArray() throws Exception {
        Doacao doacao = builder.contruir();
        mvc.perform(get("/api/doacoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void buscarDoacoes_MetodoGet_deveRetornarUmaDoacaoDTO() throws Exception {
        Doacao doacao = builder.contruir();

        mvc.perform(get("/api/doacoes/{id}", doacao.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(doacao.getId()));

        mvc.perform(get("/api/doacoes/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void buscarDoacao_MetodoGet_deveRetornarUmaExceptionNaoEncontrado() throws Exception {
        mvc.perform(get("/api/doacoes/9999"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void salvarUmaDoacao_MetodoPost_deveRetornarUmaDoacaoDTO() throws Exception {
        Doacao doacao = builder.construirEntidade();

        mvc.perform(post("/api/doacoes")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(doacao)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void EditarUmaDoacao_MetodoPost_deveRetornarUmaDoacaoDTO() throws Exception {
        Doacao doacao = builder.contruir();

        doacao.setObservacao("THAIS");

        mvc.perform(put("/api/doacoes")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(doacao)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(doacao.getId()))
                .andExpect(jsonPath("$.nomeDoador").value(doacao.getNomeDoador()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deletarDoacao_MetodoDelete() throws Exception {
        Doacao doacao = builder.contruir();

        mvc.perform(delete("/api/doacoes/{id}", doacao.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
