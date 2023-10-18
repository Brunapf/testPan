package com.project.testPan.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StateAddress {
    private String id;
    private String sigla;
    private String nome;
    private Regiao regiao;

}
