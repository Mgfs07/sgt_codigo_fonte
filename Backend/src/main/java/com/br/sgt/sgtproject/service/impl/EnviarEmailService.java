package com.br.sgt.sgtproject.service.impl;

import com.br.sgt.sgtproject.service.dto.GastoListDTO;
import com.br.sgt.sgtproject.service.dto.PagamentoColaboradorListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnviarEmailService {

    private final JavaMailSender envioEmailJava;


    public void gasto(GastoListDTO gastoDTO){
        log.info("Enviando Email");
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo("cruvmercenarios@gmail.com");
        mensagem.setSubject("Retirada da caixa do Clube");
        mensagem.setText("Retirada da Tesouraria o valor de R$" + gastoDTO.getValorRetirado() +
                "no dia " + gastoDTO.getDataDispesa() + "solicitado por " + gastoDTO.getNomeColaborador() +
                "para o fim de " + gastoDTO.getMotivo());
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }

    public void pagamento(PagamentoColaboradorListDTO pagamento, String emailColaborador){
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailColaborador);
        mensagem.setSubject("Pagamento do(e) " + pagamento.getNomePagamento());
        mensagem.setText("Olá " + pagamento.getNomeColaborador() + "\nVoce pagou o(a) " + pagamento.getNomePagamento()  +
                "No dia " + pagamento.getDataPagamento() +
                "no valor de R$" + pagamento.getValorPago());
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }

}
