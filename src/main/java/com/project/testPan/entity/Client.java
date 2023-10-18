package com.project.testPan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long id;
    private String name;
    private String cpf;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "client_product",
    joinColumns = {@JoinColumn(name = "client_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
    private String cep;
    private String nameStreet;
    private Integer numberStreet;
    private String neighborhood;
    private Status status;
    private String city;

}
