package com.br.sgt.sgtproject.builder;

public abstract class ConstruirEntidade <T>{

    public T contruir(){
        final T entity = construirEntidade();
        return persistir(entity);
    }

    public abstract T persistir(T entity);
    public abstract T construirEntidade();
}
