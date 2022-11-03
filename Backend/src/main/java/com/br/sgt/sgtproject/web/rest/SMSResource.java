package com.br.sgt.sgtproject.web.rest;

import com.br.sgt.sgtproject.service.impl.EnviarSMS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enviarEmail")
@RequiredArgsConstructor
public class SMSResource {

    private final EnviarSMS enviarSMS;


    @GetMapping()
    public ResponseEntity<Void> enviar(){
        enviarSMS.sendSMS();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
