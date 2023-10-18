package com.project.testPan.service;

import com.project.testPan.entity.Address;
import com.project.testPan.excetion.AddressException;
import com.project.testPan.repository.AddressRepository;
import com.project.testPan.repository.feign.CepFeign;
import com.project.testPan.repository.feign.CitytFeign;
import com.project.testPan.repository.feign.StateFeign;
import com.project.testPan.request.AddressRequest;
import com.project.testPan.response.EnderecoCep;
import com.project.testPan.entity.Client;
import com.project.testPan.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final ClientRepository repositoryClient;
    private final CepFeign cepFeign;
    private final StateFeign stateFeign;
    private final CitytFeign citytFeign;
    public Address create(AddressRequest addressRequest){
        return repository.save(
                Address.builder()
                .state(addressRequest.getState())
                .name(addressRequest.getName())
                .country(addressRequest.getCountry())
                .number(addressRequest.getNumber())
                .build()
        );
    }

    public Address findByCPF(String cpf) {
        Optional<Client> obj = repositoryClient.findByCpf(cpf);
        return obj.get().getAddress();
    }

    public EnderecoCep findAddressByCep(String cep){
        return cepFeign.findAddressByCep(cep);
    }
    @Cacheable("address")
    public List<Address> getAll(){
        return repository.findAll();
    }

    public Address update(AddressRequest addressRequest){
        try {
            Optional<Address> cli = repository.findById(addressRequest.getId());
            updateAddress(cli,
                    Address.builder()
                            .state(addressRequest.getState())
                            .name(addressRequest.getName())
                            .country(addressRequest.getCountry())
                            .number(addressRequest.getNumber())
                            .build()
            );
            return repository.save(cli.get());
        } catch (final Exception e){
            throw new AddressException("Error to update.");
        }

    }

    private void updateAddress(Optional<Address> cli, Address address) {
        cli.get().setName(address.getName());
    }

    @Cacheable("states")
    public Object findStates() {
        return stateFeign.findStates();
    }
    @Cacheable("citys")
    public Object findCityByIdUF(String id) {
        return citytFeign.findCityByUF(id);
    }
}
