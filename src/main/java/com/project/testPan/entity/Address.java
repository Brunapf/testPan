package com.project.testPan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer number;
    private String cep;
    private String state;
    private String country;
}
