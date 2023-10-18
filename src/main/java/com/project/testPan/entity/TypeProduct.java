package com.project.testPan.entity;

import lombok.Getter;

@Getter
public enum TypeProduct {

    CARTAO(0), EMPRESTIMO(1), CAPITALIZACAO(2), INVESTIMENTOS(3);

    private final int valor;
    TypeProduct(int value){
        valor = value;
    }
}
