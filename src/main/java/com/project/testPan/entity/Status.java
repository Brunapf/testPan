package com.project.testPan.entity;

import lombok.Getter;

@Getter
public enum Status {

    ACTIVE(0), DISABLE(1), BLOCKED(2);

    private final int valor;
    Status(int value){
        valor = value;
    }
}
