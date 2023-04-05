package com.br.sgt.sgtproject.domain.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiretoriaExecutiva {

    SUELY("suelybrunhara@gmail.com"),
    ALLAN("allan-simoes@outlook.com"),
    CLEUCIANO("cleucianomarques@hotmail.com"),
    MYLENA("mylenacardoso01@outlook.com"),
    HAYNNER("haynner.ferreira.ll@hotmail.com");

    private final String email;

    public static String[] buscarEmails() {
        return new String[]{
                CLEUCIANO.getEmail(), MYLENA.getEmail(),
                HAYNNER.getEmail(), SUELY.getEmail(), ALLAN.getEmail()
        };
    }
}
