package com.project.testPan.service;

import com.project.testPan.entity.Client;
import com.project.testPan.entity.Product;
import com.project.testPan.repository.ClientRepository;
import com.project.testPan.repository.ProductRepository;
import com.project.testPan.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ProductRepository productRepository;

    public Client create(ClientRequest client){
        return repository.save(
                Client.builder()
                        .name(client.getName())
                        .products(client.getProducts())
                        .status(client.getStatus())
                        .cpf(client.getCpf())
                        .build()
        );
    }

    public Client findByCPF(String cpf) {
        Optional<Client> obj = repository.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException(Client.class,"Objeto não encontrado! CPF: " + cpf));
    }

    public Client findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(Client.class,"Objeto não encontrado! ID: " + id));
    }

    public List<Client> getAll(){
        return repository.findAll();
    }

    public Client update(ClientRequest client, String cpf) {
        repository
                .findByCpf(cpf)
                .ifPresent(newClient -> {
                    newClient.setName(client.getName());
                    newClient.setStatus(client.getStatus());
                    newClient.setAddress(client.getAddress());
                    if(!client.getProducts().isEmpty()){

                        for (Product product: client.getProducts()) {
                            Optional<Product> prod1 = productRepository.findById(product.getId());
                            prod1.ifPresent(value -> newClient.getProducts().add(value));
                        }
                    }

                    repository.save(newClient);
                });
       return repository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(Client.class,"Objeto não encontrado! CPF: " + cpf));
    }


}
