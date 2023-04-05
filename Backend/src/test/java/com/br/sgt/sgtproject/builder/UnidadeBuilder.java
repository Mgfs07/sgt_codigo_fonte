package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Unidade;
import com.br.sgt.sgtproject.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadeBuilder extends ConstruirEntidade<Unidade> {

    @Autowired
    private UnidadeRepository repository;


    @Override
    public Unidade construirEntidade() {
        Unidade unidade = new Unidade();
        unidade.setId(10);
        unidade.setNomeUnidade("Diretoria");
        return unidade;
    }

    @Override
    public Unidade persistir(Unidade entity) {
        return repository.save(entity);
    }
}
