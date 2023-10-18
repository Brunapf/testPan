package com.project.testPan.request;

import com.project.testPan.entity.Client;
import com.project.testPan.entity.Status;
import com.project.testPan.entity.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long id;
    private String name;
    private TypeProduct type;
    private Status status;
    private List<Client> clients;
}
