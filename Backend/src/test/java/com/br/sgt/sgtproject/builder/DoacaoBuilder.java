package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.service.dto.DoacaoListDTO;

import java.time.LocalDate;

public class DoacaoBuilder {

    private DoacaoBuilder(){

    }

    private Doacao doacao;
    private DoacaoListDTO doacaoList;


    public static DoacaoBuilder umaDoacao(){
        DoacaoBuilder builder = new DoacaoBuilder();
        builder.doacao = new Doacao();
        return builder;
    }

    public static DoacaoBuilder umaDoacaoList() {
        DoacaoBuilder builder = new DoacaoBuilder();
        builder.doacaoList = new DoacaoListDTO();
        return builder;
    }

    public DoacaoBuilder todosAtributo() {
        doacao.setId(1);
        doacao.setNomeDoador("Matheus");
        doacao.setObservacao("nenhuma");
        doacao.setDoadoPara(PagamentoBuilder.umPagamento().todosAtributos().builder());
        doacao.setValorDoado(100.00);
        doacao.setDataDoacao(LocalDate.of(2022,12,10));
        return this;
    }

    public DoacaoBuilder todosAtributoList() {
        doacaoList.setId(1);
        doacaoList.setNomeDoador("Matheus");
        doacaoList.setObservacao("nenhum");
        doacaoList.setDoadoParaPagamento("Campori");
        doacaoList.setValorDoado(100.00);
        doacaoList.setDataDoacao(LocalDate.of(2022,12,10));
        return this;
    }

    public Doacao builder(){
        return this.doacao;
    }

    public DoacaoListDTO builderList(){
        return this.doacaoList;
    }
}
