package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Gasto;
import com.br.sgt.sgtproject.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GastoBuilder extends ConstruirEntidade<Gasto> {

    @Autowired
    private GastoRepository repository;

    @Autowired
    private ColaboradorBuilder colaboradorBuilder;

    @Autowired
    private PagamentoBuilder pagamentoBuilder;

    public Gasto construirEntidade(){
        Gasto gasto = new Gasto();
        gasto.setMotivo("Compra Barraca");
        gasto.setDescricao("Descricao");
        gasto.setColaborador(colaboradorBuilder.contruir());
        gasto.setDataDispesa(LocalDate.of(2022,11,20));
        gasto.setValorRetirado(15.00);
        gasto.setComprovante("foto");
        gasto.setRetiradoDe(pagamentoBuilder.contruir());
        return gasto;
    }


    @Override
    public Gasto persistir(Gasto entity) {
        return repository.save(entity);
    }
}
