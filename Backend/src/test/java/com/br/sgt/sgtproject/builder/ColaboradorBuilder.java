package com.br.sgt.sgtproject.builder;


import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Unidade;
import com.br.sgt.sgtproject.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorBuilder extends ConstruirEntidade<Colaborador> {


    @Autowired
    private ColaboradorRepository repository;
    @Autowired
    private UnidadeBuilder unidadeBuilder;

    @Override
    public Colaborador construirEntidade() {
        Colaborador colaborador = new Colaborador();
        Unidade unidade = unidadeBuilder.contruir();
        colaborador.setNomeColaborador("Matheus");
        colaborador.setUnidade(unidade);
        colaborador.setEmail("teste@gmail.com");
        colaborador.setAtivo(true);
        colaborador.setTelefone("99999988");
        return colaborador;
    }

    @Override
    public Colaborador persistir(Colaborador entity) {
        return repository.save(entity);
    }
}
