package com.project.testPan.service;

import com.project.testPan.entity.Client;
import com.project.testPan.entity.Product;
import com.project.testPan.excetion.ProductException;
import com.project.testPan.repository.ClientRepository;
import com.project.testPan.repository.ProductRepository;
import com.project.testPan.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ClientRepository repositoryClient;

    public Product create(ProductRequest product){
        return repository.save(
                Product.builder()
                .name(product.getName())
                .clients(product.getClients())
                .type(product.getType())
                .status(product.getStatus())
                .build()
        );
    }

    public List<Product> findByCPF(String cpf) {
        try {
            notNull(cpf, "CPF is required");
            Optional<Client> obj = repositoryClient.findByCpf(cpf);

            return obj.get().getProducts();
        } catch (final Exception e) {
            throw new ProductException("Error to find CPF.");
        }

    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(Product.class,"Object not find. Id: " + id));

    }
    @Cacheable("products")
    public List<Product> getAll(){
        return repository.findAll();
    }

    public Product update(ProductRequest product, Long id){
        try {
            repository
                    .findById(id)
                    .ifPresent(newProduct -> {
                        newProduct.setName(product.getName());
                        newProduct.setStatus(product.getStatus());

                        repository.save(newProduct);
                    });
            return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Product.class,"Object not find. Id: " + id));
        } catch (final Exception e){
            throw new ProductException("Error in update of Product");
        }


    }


}
