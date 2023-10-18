package com.project.testPan.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regiao {

    private Integer id;
    private String sigla;
    private String nome;
}
