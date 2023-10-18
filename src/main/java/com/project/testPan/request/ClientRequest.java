package com.project.testPan.request;

import com.project.testPan.entity.Address;
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
    private transient List<Product> products;
    private transient Address address;
    private Status status;
}
