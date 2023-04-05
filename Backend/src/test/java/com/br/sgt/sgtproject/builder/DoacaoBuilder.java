package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Doacao;
import com.br.sgt.sgtproject.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DoacaoBuilder extends ConstruirEntidade<Doacao> {

    @Autowired
    private DoacaoRepository repository;

    @Autowired
    private PagamentoBuilder pagamentoBuilder;


    @Override
    public Doacao persistir(Doacao entity) {
        return repository.save(entity);
    }

    @Override
    public Doacao construirEntidade() {
        Doacao doacao = new Doacao();
        doacao.setNomeDoador("Matheus");
        doacao.setObservacao("nenhuma");
        doacao.setDoadoPara(pagamentoBuilder.contruir());
        doacao.setValorDoado(100.00);
        doacao.setDataDoacao(LocalDate.of(2022,12,10));
        return doacao;
    }
}
