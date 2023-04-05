package com.br.sgt.sgtproject.web;

import com.br.sgt.sgtproject.builder.PagamentoBuilder;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.service.impl.util.TesteUtil;
import com.br.sgt.sgtproject.service.mapper.PagamentoMapper;
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
public class PagamentoResourceTest {

    @Autowired
    private PagamentoBuilder builder;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PagamentoMapper mapper;

    @Test
    public void buscarPagamento_MetodoGet_deveRetornarArray() throws Exception {
        Pagamento pagamento = builder.contruir();
        mvc.perform(get("/api/pagamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void buscarPagamento_MetodoGet_deveRetornarUmPagamentoDTO() throws Exception {
        Pagamento pagamento = builder.contruir();

        mvc.perform(get("/api/pagamentos/{id}", pagamento.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pagamento.getId()));

        mvc.perform(get("/api/pagamentos/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void buscarPagamento_MetodoGet_deveRetornarUmaExceptionNaoEncontrado() throws Exception {
        mvc.perform(get("/api/pagamentos/9999"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void salvarUmPagamento_MetodoPost_deveRetornarUmPagamentoDTO() throws Exception {
        Pagamento pagamento = builder.construirEntidade();

        mvc.perform(post("/api/pagamentos")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(pagamento)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void EditarUmPagamento_MetodoPost_deveRetornarUmPagamentoDTO() throws Exception {
        Pagamento pagamento = builder.contruir();
        pagamento.setNomePagamento("OUTRO");

        mvc.perform(put("/api/pagamentos")
                        .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(pagamento)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pagamento.getId()))
                .andExpect(jsonPath("$.nomePagamento").value(pagamento.getNomePagamento()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deletarPagamento_MetodoDelete() throws Exception {
        Pagamento pagamento = builder.contruir();

        mvc.perform(delete("/api/pagamentos/{id}", pagamento.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
