package com.br.sgt.sgtproject.builder;


import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;

public class ColaboradorBuilder {

    private ColaboradorBuilder(){

    }

    private Colaborador colaborador;
    private ColaboradorListDTO colaboradorList;

    public static ColaboradorBuilder umColaborador() {
        ColaboradorBuilder builder = new ColaboradorBuilder();
        builder.colaborador = new Colaborador();
        return builder;
    }

    public static ColaboradorBuilder umColaboradorList() {
        ColaboradorBuilder builder = new ColaboradorBuilder();
        builder.colaboradorList = new ColaboradorListDTO();
        return builder;
    }

    public ColaboradorBuilder todosAtributos() {
        colaborador.setId(1);
        colaborador.setNomeColaborador("Matheus");
        colaborador.setUnidade(UnidadeBuilder.umaUnidade().todosAtributos().builder());
        colaborador.setEmail("teste@gmail.com");
        colaborador.setAtivo(true);
        colaborador.setTelefone("99999988");
        return this;
    }

    public ColaboradorBuilder todosAtributosList() {
        colaboradorList.setId(1);
        colaboradorList.setNomeColaborador("Matheus");
        colaboradorList.setNomeUnidade("Diretoria");
        colaboradorList.setTelefone("99999988");
        return this;
    }

    public Colaborador builder() {
        return this.colaborador;
    }

    public ColaboradorListDTO builderList() {
        return this.colaboradorList;
    }

}
