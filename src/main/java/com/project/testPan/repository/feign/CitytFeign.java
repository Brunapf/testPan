package com.project.testPan.repository.feign;

import com.project.testPan.response.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/", name = "viacep")
public interface CitytFeign {
    @GetMapping("{id}/municipios")
    List<City> findCityByUF(@PathVariable("id") String id);
}
