package com.project.testPan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private TypeProduct type;
    private Status status;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Client> clients;
}
