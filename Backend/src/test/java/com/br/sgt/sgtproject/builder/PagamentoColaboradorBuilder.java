package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;

import java.time.LocalDateTime;

public class PagamentoColaboradorBuilder {

    private PagamentoColaboradorBuilder(){

    }

    private PagamentoColaborador pagamentoColaborador;
    private PagamentoColaboradorListDTO pagamentoColaboradorList;

    static PagamentoColaboradorBuilder umPagamentoColaborador(){
        PagamentoColaboradorBuilder builder = new PagamentoColaboradorBuilder();
        builder.pagamentoColaborador = new PagamentoColaborador();
        return builder;
    }

    static PagamentoColaboradorBuilder umPagamentoColaboradorList(){
        PagamentoColaboradorBuilder builder = new PagamentoColaboradorBuilder();
        builder.pagamentoColaboradorList = new PagamentoColaboradorListDTO();
        return builder;
    }

    public PagamentoColaboradorBuilder todosAtributos(){
        pagamentoColaborador.setId(1);
        pagamentoColaborador.setColaborador(ColaboradorBuilder.umColaborador().todosAtributos().builder());
        pagamentoColaborador.setPagamento(PagamentoBuilder.umPagamento().todosAtributos().builder());
        pagamentoColaborador.setObservacao("Nenhuma");
        pagamentoColaborador.setRetiradoLugar(true);
        pagamentoColaborador.setPagamentoRetirado(PagamentoBuilder.umPagamento().todosAtributos().builder());
        pagamentoColaborador.setDataPagamento(LocalDateTime.now());
        pagamentoColaborador.setValorPago(100.00);
        return this;
    }

    public PagamentoColaboradorBuilder todosAtributosList(){
        pagamentoColaboradorList.setId(1);
        pagamentoColaboradorList.setNomeColaborador(ColaboradorBuilder.umColaborador().todosAtributos().builder().getNomeColaborador());
        pagamentoColaboradorList.setNomePagamento(PagamentoBuilder.umPagamento().todosAtributos().builder().getNomePagamento());
        pagamentoColaboradorList.setValorPago(50.00);
        return this;
    }

    public PagamentoColaborador builder(){
        return this.pagamentoColaborador;
    }

    public PagamentoColaboradorListDTO builderList(){
        return this.pagamentoColaboradorList;
    }


}
