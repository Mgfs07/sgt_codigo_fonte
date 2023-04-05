package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoBuilder extends ConstruirEntidade<Pagamento> {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento construirEntidade(){
        Pagamento pagamento = new Pagamento();
        pagamento.setNomePagamento("TESTE");
        pagamento.setValorMeta(50.00);
        pagamento.setAtivo(true);
        return pagamento;
    }

    @Override
    public Pagamento persistir(Pagamento entity) {
        return repository.save(entity);
    }
}
