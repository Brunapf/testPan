package com.project.testPan.controller;

import com.project.testPan.entity.Product;
import com.project.testPan.request.ProductRequest;
import com.project.testPan.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest product){
        log.info("[CREATE PRODUCT] insert() called - product: {}", product);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(product));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        log.info("[GET PRODUCT BY ID]  - id: {}", id);
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        log.info("[GET PRODUCTS ]");
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductRequest product){
        log.info("[UPDATE PRODUCT]   called - id: {}", id);
        return ResponseEntity.ok().body(service.update(product, id));
    }

    @GetMapping(value = "/findByCPF/{cpf}")
    public ResponseEntity<List<Product>> getProductByCPF(@PathVariable String cpf){
        log.info("[FIND PRODUCT BY CPF]   called - cpf: {}", cpf);
        return ResponseEntity.ok().body(service.findByCPF(cpf));
    }

}
