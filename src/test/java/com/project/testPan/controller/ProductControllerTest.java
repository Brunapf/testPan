package com.project.testPan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.testPan.entity.Status;
import com.project.testPan.entity.TypeProduct;
import com.project.testPan.request.ProductRequest;
import com.project.testPan.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService service;
    private static final String PRODUCT_API_URL_PATH = "/product";
    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void getAllSucess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product")
         )
                .andDo(print())
                .andExpect(status().isOk()
                );
    }

    @Test
    public void createEmployeeAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/product")
                        .content(asJsonString(new ProductRequest(1L,"Test",TypeProduct.CARTAO, Status.ACTIVE,null)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
