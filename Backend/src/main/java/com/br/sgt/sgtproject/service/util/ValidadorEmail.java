package com.br.sgt.sgtproject.service.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@UtilityClass
public class ValidadorEmail {

    private static final String EMAIL_INCORRETO = "Favor adicionar um email válido";

    public static void emailValido(String email){
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMAIL_INCORRETO);
        }
    }
}
