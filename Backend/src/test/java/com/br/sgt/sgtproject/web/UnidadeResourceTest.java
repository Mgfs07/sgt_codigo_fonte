package com.br.sgt.sgtproject.web;

import com.br.sgt.sgtproject.builder.UnidadeBuilder;
import com.br.sgt.sgtproject.domain.Unidade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UnidadeResourceTest {

    @Autowired
    private UnidadeBuilder builder;

    @Autowired
    private MockMvc mvc;

    @Test
    public void buscarUnidade_MetodoGet_deveRetornarArray() throws Exception {
        Unidade unidade = builder.contruir();
        mvc.perform(get("/api/unidade/select"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
