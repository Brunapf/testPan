package com.project.testPan.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MicrorRegiao {
    private Integer id;
    private String nome;
    private MesorRegiao mesorregiao;

}
