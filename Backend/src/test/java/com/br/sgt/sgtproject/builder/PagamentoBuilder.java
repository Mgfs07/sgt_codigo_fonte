package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Pagamento;

public class PagamentoBuilder {

    private PagamentoBuilder(){
    }

    private Pagamento pagamento;

    public static PagamentoBuilder umPagamento(){
        PagamentoBuilder builder = new PagamentoBuilder();
        builder.pagamento = new Pagamento();
        return builder;
    }

    public PagamentoBuilder todosAtributos(){
        pagamento.setId(1);
        pagamento.setNomePagamento("Acampamento");
        pagamento.setValorMeta(50.00);
        pagamento.setAtivo(true);
        return this;
    }

    public Pagamento builder() {
        return this.pagamento;
    }
}
