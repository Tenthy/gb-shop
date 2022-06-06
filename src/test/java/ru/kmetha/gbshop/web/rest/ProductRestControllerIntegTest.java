package ru.kmetha.gbshop.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.kmetha.gbapi.category.dto.CategoryDto;
import ru.kmetha.gbapi.common.enums.Status;
import ru.kmetha.gbapi.product.dto.ProductDto;
import ru.kmetha.gbshop.dao.ProductDao;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRestControllerIntegTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductDao productDao;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void saveTest() throws Exception {
        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ProductDto.builder()
                                .id(1L)
                                .title("Meat")
                                .cost(BigDecimal.valueOf(50.59))
                                .manufactureDate(LocalDate.now())
                                .status(Status.ACTIVE)
                                .manufacturer("Fresh Frost Inc.")
                                .build())))
                .andExpect(status().isCreated());

        assertEquals(1, productDao.findAll().size());
    }

    @Test
    @Order(2)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(content().string(contains("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value("Meat"));
    }
}