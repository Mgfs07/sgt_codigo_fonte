package com.br.sgt.sgtproject.web;


import com.br.sgt.sgtproject.builder.ColaboradorBuilder;
import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.impl.util.TesteUtil;
import com.br.sgt.sgtproject.service.mapper.ColaboradorMapper;
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
public class ColaboradorResourceTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ColaboradorBuilder builder;
    @Autowired
    private ColaboradorMapper mapper;

    @Test
    public void buscarColaborador_MetodoGet_deveRetornarArray() throws Exception {
        Colaborador colaborador = builder.contruir();
        mvc.perform(get("/api/colaboradores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void buscarColaborador_MetodoGet_deveRetornarUmColaboradorDTO() throws Exception {
        Colaborador colaborador = builder.contruir();

        mvc.perform(get("/api/colaboradores/{id}", colaborador.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(colaborador.getId()));

        mvc.perform(get("/api/colaboradores/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void buscarColaborador_MetodoGet_deveRetornarUmaExceptionNaoEncontrado() throws Exception {
        mvc.perform(get("/api/colaboradores/9999"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void salvarUmColaborador_MetodoPost_deveRetornarUmColaboradorDTO() throws Exception {
        Colaborador colaborador = builder.construirEntidade();
        colaborador.setNomeColaborador("JOAQUINZIM");

        mvc.perform(post("/api/colaboradores")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(colaborador)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void EditarUmColaborador_MetodoPut_deveRetornarUmColaboradorDTO() throws Exception {
        Colaborador colaborador = builder.contruir();
        colaborador.setNomeColaborador("THAIS");

        mvc.perform(put("/api/colaboradores")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(colaborador)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(colaborador.getId()))
                .andExpect(jsonPath("$.nomeColaborador").value(colaborador.getNomeColaborador()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deletarColaborador_MetodoDelete() throws Exception {
        Colaborador colaborador = builder.contruir();

        mvc.perform(delete("/api/colaboradores/{id}", colaborador.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void buscarDropdownColaborador_MetodoGet_deveRetornarArray() throws Exception {
        Colaborador colaborador = builder.contruir();
        mvc.perform(get("/api/colaboradores/select"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
