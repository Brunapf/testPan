package com.project.testPan.service;

import com.project.testPan.repository.ClientRepository;
import com.project.testPan.repository.feign.CepFeign;
import com.project.testPan.repository.feign.CitytFeign;
import com.project.testPan.repository.feign.StateFeign;
import com.project.testPan.response.EnderecoCep;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ClientRepository repositoryClient;
    private final CepFeign cepFeign;
    private final StateFeign stateFeign;
    private final CitytFeign citytFeign;

    public EnderecoCep findAddressByCep(String cep){
        return cepFeign.findAddressByCep(cep);
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
