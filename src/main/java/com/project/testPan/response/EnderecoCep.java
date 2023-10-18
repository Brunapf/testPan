package com.project.testPan.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoCep {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;

}
