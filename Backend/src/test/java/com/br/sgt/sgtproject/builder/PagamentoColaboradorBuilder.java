package com.br.sgt.sgtproject.builder;

import com.br.sgt.sgtproject.domain.Colaborador;
import com.br.sgt.sgtproject.domain.Pagamento;
import com.br.sgt.sgtproject.domain.PagamentoColaborador;
import com.br.sgt.sgtproject.repository.PagamentoColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PagamentoColaboradorBuilder extends ConstruirEntidade<PagamentoColaborador> {

    @Autowired
    private PagamentoColaboradorRepository repository;

    @Autowired
    private ColaboradorBuilder colaboradorBuilder;

    @Autowired
    private PagamentoBuilder pagamentoBuilder;


    public PagamentoColaborador construirEntidade(){
        Colaborador colaborador = colaboradorBuilder.contruir();
        Pagamento pagamento = pagamentoBuilder.contruir();
        PagamentoColaborador pagamentoColaborador = new PagamentoColaborador();
        pagamentoColaborador.setColaborador(colaborador);
        pagamentoColaborador.setPagamento(pagamento);
        pagamentoColaborador.setObservacao("Nenhuma");
        pagamentoColaborador.setRetiradoLugar(false);
        pagamentoColaborador.setPagamentoRetirado(null);
        pagamentoColaborador.setDataPagamento(LocalDate.now());
        pagamentoColaborador.setValorPago(100.00);
        return pagamentoColaborador;
    }


    @Override
    public PagamentoColaborador persistir(PagamentoColaborador entity) {
        return repository.save(entity);
    }
}
