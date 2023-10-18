package com.project.testPan.service;

import com.project.testPan.entity.Client;
import com.project.testPan.entity.Product;
import com.project.testPan.excetion.ProductException;
import com.project.testPan.repository.ClientRepository;
import com.project.testPan.repository.ProductRepository;
import com.project.testPan.repository.feign.CepFeign;
import com.project.testPan.request.ClientRequest;
import com.project.testPan.response.EnderecoCep;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ProductRepository productRepository;
    private final CepFeign cepFeign;
    public Client create(ClientRequest client){
        Client saveClient = new Client();
        saveClient = validate(client, saveClient);
        return repository.save(saveClient);
    }

    private Client validate(ClientRequest clientRequest, Client saveClient) {
        saveClient = Client.builder()
                .name(clientRequest.getName().isEmpty() ? saveClient.getName() : clientRequest.getName())
                .status(clientRequest.getStatus())
                .cpf(clientRequest.getCpf().isEmpty() ? saveClient.getCpf() : clientRequest.getCpf())
                .cep(null == clientRequest.getCep() ? saveClient.getCep() : clientRequest.getCep())
                .numberStreet(clientRequest.getNumberStreet()== null ? saveClient.getNumberStreet() : clientRequest.getNumberStreet())
                .build();
        if(null != clientRequest.getCep()){
            validateAddress(clientRequest, saveClient);
        }
        if(null != clientRequest.getProducts()){
            validateProduct(clientRequest, saveClient);
        }

        return saveClient;
    }

    private void validateProduct(ClientRequest client, Client saveClient) {
        if(!client.getProducts().isEmpty()){
            List<Product> p = new ArrayList<>();
            client.getProducts().stream().forEach(n -> {

                Optional<Product> product = productRepository.findById(n.getId());
                product.ifPresent(value -> p.add(Product.builder().id(
                                value.getId())
                        .name(value.getName())
                        .type(value.getType())
                        .status(value.getStatus())
                        .build()));

            });
            saveClient.setProducts(p);

        }
    }

    private void validateAddress(ClientRequest client, Client saveClient) {
        EnderecoCep address = cepFeign.findAddressByCep(client.getCep());
        if(address != null){
            saveClient.setCep(address.getCep());
            saveClient.setNameStreet(address.getLogradouro());
            saveClient.setNeighborhood(address.getBairro());
            saveClient.setCity(address.getLocalidade());
        }
    }

    public Client findByCPF(String cpf) {
        Optional<Client> obj = repository.findByCpf(cpf);
        return obj.stream().findFirst().orElseThrow(() -> new ObjectNotFoundException(Client.class,"Objeto não encontrado! CPF: " + cpf));
    }

    public Client findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        return obj.get();
    }
    @Cacheable("clients")
    public List<Client> getAll(){
        return repository.findAll();
    }

    public Client update(ClientRequest client, String cpf) {
        try {
            repository.findByCpf(cpf)
                    .ifPresent(newProduct -> {
                        if(client.getName() != null && !client.getName().equalsIgnoreCase(newProduct.getName())){
                            newProduct.setName(client.getName());
                        }
                        if(client.getStatus() != null && !client.getStatus().name().equalsIgnoreCase(newProduct.getStatus().name())){
                            newProduct.setStatus(client.getStatus());
                        }

                        if(null != client.getCep()){
                            validateAddress(client, newProduct);
                        }
                        if(null != client.getProducts()){
                            validateProduct(client, newProduct);
                        }
                        repository.save(newProduct);
                    });
            return repository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(Product.class,"Object not find. cpf: " + cpf));


        }catch (Exception e){
            throw new ProductException("Error in update of client");
        }
    }


}
