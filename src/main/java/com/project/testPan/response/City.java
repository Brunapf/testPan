package com.project.testPan.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private Integer id;
    private String nome;
    private MicrorRegiao microrregiao;

}
