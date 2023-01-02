package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.service.dto.GastoListDTO;

import java.time.LocalDate;

public class GastoBuilder {


    private GastoBuilder() {

    }

    private Gasto gasto;
    private GastoListDTO gastoList;

    public GastoBuilder umGasto(){
        GastoBuilder builder = new GastoBuilder();
        builder.gasto = new Gasto();
        return builder;
    }

    public GastoBuilder umGastoList(){
        GastoBuilder builder = new GastoBuilder();
        builder.gastoList = new GastoListDTO();
        return builder;
    }

    public GastoBuilder todosAtributos(){
        gasto.setId(1);
        gasto.setMotivo("Compra Barraca");
        gasto.setDescricao("Descricao");
        gasto.setColaborador(ColaboradorBuilder.umColaborador().todosAtributos().builder());
        gasto.setDataDispesa(LocalDate.of(2022,11,20));
        gasto.setValorRetirado(15.00);
        gasto.setComprovante("foto");
        gasto.setRetiradoDe(PagamentoBuilder.umPagamento().todosAtributos().builder());
        return this;
    }

    public GastoBuilder todosAtributosList(){
        gastoList.setId(1);
        gastoList.setMotivo("Compra");
        gastoList.setNomeColaborador("Matheus");
        gastoList.setDataDispesa(LocalDate.of(2022,11,20));
        gastoList.setValorRetirado(30.00);
        gastoList.setRetiradoDoPagamento("Campori");
        return this;
    }

    public Gasto builder(){
        return this.gasto;
    }

    public GastoListDTO builderList(){
        return this.gastoList;
    }
}
