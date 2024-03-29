package com.project.testPan.repository.feign;

import com.project.testPan.response.EnderecoCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface CepFeign {
    @GetMapping("{cep}/json")
    EnderecoCep findAddressByCep(@PathVariable("cep") String cep);
}
