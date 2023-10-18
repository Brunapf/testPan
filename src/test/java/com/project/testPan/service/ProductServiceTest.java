package com.project.testPan.service;

import com.project.testPan.entity.Client;
import com.project.testPan.entity.Product;
import com.project.testPan.entity.Status;
import com.project.testPan.entity.TypeProduct;
import com.project.testPan.excetion.ProductException;
import com.project.testPan.repository.ClientRepository;
import com.project.testPan.repository.ProductRepository;
import com.project.testPan.request.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductRepository repository;

    @Mock
    ClientRepository repositoryClient;
    Product product;
    Client client;
    @BeforeEach
    public void setup(){
        product = Product.builder().id(1L).name("CREDITO MASTER").status(Status.ACTIVE).type(TypeProduct.CARTAO).build();
        client = Client.builder().id(1L).cpf("123456789101").products(List.of(product)).build();
    }

//    @Test
//    void create(){
//        when(repository.save(product)).thenReturn(product);
//
//        Product prod = service.create(ProductRequest.builder()
//                .name(product.getName())
//                .clients(product.getClients())
//                .type(product.getType())
//                .status(product.getStatus())
//                .build());
//
//        assertEquals(prod,product);
//
//    }

    @Test
    void findProductByCPFWithSuccess(){
        when(repositoryClient.findByCpf("123456789101")).thenReturn(Optional.of(client));
        List<Product> prod = service.findByCPF("123456789101");

        assertEquals(prod, Collections.singletonList(product));
        verify(repositoryClient).findByCpf("123456789101");
        verifyNoMoreInteractions(repositoryClient);
    }

    @Test
    void noCallRepositoryCaseCPFIsNull(){
        final ProductException e = assertThrows(ProductException.class, () ->{
            service.findByCPF(null);
        });

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Error to find CPF."));
        verifyNoInteractions(repositoryClient);
    }

    @Test
    void shouldCallExceptionWhenRepositoryFail(){
        when(repositoryClient.findByCpf(client.getCpf())).thenThrow(new RuntimeException("Fail"));
        final ProductException e = assertThrows(ProductException.class, () ->{
            service.findByCPF(client.getCpf());
        });
    assertThat(e.getMessage(), is("Error to find CPF."));
    }

}
