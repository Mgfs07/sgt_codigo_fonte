package com.br.sgt.sgtproject.service;

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

    public void enviar(){
        log.info("Enviando Email");
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo("cruvmercenarios@gmail.com");
        mensagem.setSubject("TESTE");
        mensagem.setText("TESTE");
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }
}
