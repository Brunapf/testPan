package com.project.testPan.controller;

import com.project.testPan.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor
public class AddressController {
   private final AddressService service;

    @GetMapping(value = "/findCep")
    public ResponseEntity getAddressByCep(@RequestParam(value="cep", required=true, name = "cep") String cep){
        log.info("[FIND CEP]  called- cep: {}", cep);
        return ResponseEntity.ok().body(service.findAddressByCep(cep));
    }

    @GetMapping(value = "/findStates")
    public ResponseEntity getAllStates(){
        log.info("[FIND STATES]  called ");
        return ResponseEntity.ok().body(service.findStates());
    }

    @GetMapping(value = "/findCityByIdUF/{id}")
    public ResponseEntity getCityByIdUF(@PathVariable String id){
        log.info("[find City By Id UF ]  called - UF: {}", id);
        return ResponseEntity.ok().body(service.findCityByIdUF(id));
    }
}
