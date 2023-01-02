package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Unidade;

public class UnidadeBuilder {

    private UnidadeBuilder(){

    }


    private Unidade unidade;

    public static UnidadeBuilder umaUnidade(){
        UnidadeBuilder builder = new UnidadeBuilder();
        builder.unidade = new Unidade();
        return builder;
    }

    public UnidadeBuilder todosAtributos(){
        unidade.setId(1);
        unidade.setNomeUnidade("Diretoria");
        return this;
    }

    public Unidade builder(){
        return this.unidade;
    }
}
