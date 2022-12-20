//package com.br.sgt.sgtproject.builder;
//
//
//import com.br.sgt.sgtproject.domain.Colaborador;
//import com.br.sgt.sgtproject.domain.Unidade;
//import com.br.sgt.sgtproject.service.dto.ColaboradorListDTO;
//
//public class ColaboradorBuilder {
//
//    private ColaboradorBuilder(){
//
//    }
//
//    private Colaborador colaborador;
//    private ColaboradorListDTO colaboradorListDTO;
//
//    public static ColaboradorBuilder umColaborador() {
//        ColaboradorBuilder builder = new ColaboradorBuilder();
//        builder.colaborador = new Colaborador();
//        return builder;
//    }
//
//    public static ColaboradorBuilder umColaboradorList() {
//        ColaboradorBuilder builder = new ColaboradorBuilder();
//        builder.colaboradorListDTO = new ColaboradorListDTO();
//        return builder;
//    }
//
//    public ColaboradorBuilder comTodoAtributosUmColaborador() {
//        colaborador.setId(1);
//        colaborador.setNomeColaborador("Matheus");
//        colaborador.setUnidade(new Unidade(1, "Diretoria"));
//        colaborador.setEmail("teste@gmail.com");
//        colaborador.setAtivo(true);
//        colaborador.setTelefone("99999988");
//        return this;
//    }
//
//    public ColaboradorBuilder comTodoAtributosUmColaboradorList() {
//        colaboradorListDTO.setId(1);
//        colaboradorListDTO.setNomeColaborador("Matheus");
//        colaboradorListDTO.setNomeUnidade("Diretoria");
//        colaboradorListDTO.setTelefone("99999988");
//        return this;
//    }
//
//    public Colaborador buildColaborador() {
//        return this.colaborador;
//    }
//
//    public ColaboradorListDTO buildColaboradorListDTO() {
//        return this.colaboradorListDTO;
//    }
//
//
//
//}
