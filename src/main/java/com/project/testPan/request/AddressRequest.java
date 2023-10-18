package com.project.testPan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private Long id;
    private String name;
    private Integer number;
    private String cep;
    private String state;
    private String country;
}
