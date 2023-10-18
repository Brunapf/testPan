package com.project.testPan.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MesorRegiao {
    private Integer id;
    private String nome;
    private UF uf;

}
