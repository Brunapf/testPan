package com.project.testPan.request;

import com.project.testPan.entity.Product;
import com.project.testPan.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private List<Product> products;
    private Status status;
    private String cep;
    private String nameStreet;
    private Integer numberStreet;
    private String neighborhood;
    private String city;
}
