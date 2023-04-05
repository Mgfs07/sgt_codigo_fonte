package com.br.sgt.sgtproject.service;

import com.br.sgt.sgtproject.domain.enun.DiretoriaExecutiva;
import com.br.sgt.sgtproject.service.dto.EmailPagamentoColaboradorDTO;
import com.br.sgt.sgtproject.service.dto.EmailGastoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnviarEmailService {

    private final JavaMailSender envioEmailJava;


    public void gasto(EmailGastoDTO gastoDTO){
        log.info("Enviando Email");
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(DiretoriaExecutiva.buscarEmails());
        mensagem.setSubject("Retirada da caixa do Clube");
        mensagem.setText("Retirada da Tesouraria " +
                "\n\nValor: R$ " + gastoDTO.getValorGasto() +
                "\n\nRetirado do Pagamento: " + gastoDTO.getRetiradoPagamento() +
                "\n\nDia: " + gastoDTO.getDataGasto() + "" +
                "\n\nSolicitado por: " + gastoDTO.getNomeSolicitador() +
                "\n\npara o fim de: " + gastoDTO.getDescricao());
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }

    public void pagamento(EmailPagamentoColaboradorDTO pagamento){
        SimpleMailMessage mensagem = new SimpleMailMessage();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        mensagem.setTo(pagamento.getEmail());
        mensagem.setSubject("Pagamento do(e) " + pagamento.getNomePagamento());
        mensagem.setText("Olá " + pagamento.getNomeColaborador() + "\n\nVoce pagou o(a) " + pagamento.getNomePagamento()  +
                "\n\nDia: " + pagamento.getDataPagamento().format(formatter) +
                "\n\nValor: R$" + pagamento.getValorPagamento());
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }


    public void doacao(EmailGastoDTO gastoDTO){
        log.info("Enviando Email");
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(DiretoriaExecutiva.buscarEmails());
        mensagem.setSubject("Retirada da caixa do Clube");
        mensagem.setText("Retirada da Tesouraria " +
                "\n\nValor: R$" + gastoDTO.getValorGasto() +
                "\n\nRetirado do Pagamento: " + gastoDTO.getRetiradoPagamento() +
                "\n\nDia " + gastoDTO.getDataGasto() + "" +
                "\n\nSolicitado por: " + gastoDTO.getNomeSolicitador() +
                "\n\npara o fim de: " + gastoDTO.getDescricao());
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }


}
