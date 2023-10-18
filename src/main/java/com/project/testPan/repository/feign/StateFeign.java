package com.project.testPan.repository.feign;

import com.project.testPan.response.StateAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/", name = "viacep")
public interface StateFeign {
    @GetMapping
    List<StateAddress> findStates();
}
