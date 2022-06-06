package ru.kmetha.gbshop.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kmetha.gbapi.category.dto.CategoryDto;
import ru.kmetha.gbapi.manufacturer.dto.ManufacturerDto;
import ru.kmetha.gbshop.service.CategoryService;
import ru.kmetha.gbshop.web.dto.mapper.CategoryMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class CategoryRestControllerMockitoTest {

    public static final String MEAT_CATEGORY = "Meat Food";
    public static final String MILK_CATEGORY = "Milk Food";

    @Mock
    CategoryService categoryService;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    CategoryRestController categoryRestController;

    MockMvc mockMvc;

    List<CategoryDto> categories;

    @BeforeEach
    void setUp() {
        categories = new ArrayList<>();
        categories.add(new CategoryDto(1L, MEAT_CATEGORY));
        categories.add(new CategoryDto(2L, MILK_CATEGORY));

        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
    }

    @Test
    void getManufacturerListMockMvcTest() throws Exception {
        //given
        given(categoryService.findAll()).willReturn(categories);

        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value(MEAT_CATEGORY))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[1].title").value(MILK_CATEGORY));
    }

    @Test
    void getManufacturerListTest() {
        //given
        given(categoryService.findAll()).willReturn(categories);
        final int categoriesSize = categories.size();

        //when
        List<CategoryDto> categoryList = categoryRestController.findAll();

        //then
        then(categoryService).should().findAll();

        assertAll(
                () -> assertEquals(categoriesSize, categoryList.size(), "Size should be " + categoriesSize),
                () -> assertEquals(MEAT_CATEGORY, categories.get(0).getTitle())
        );
    }

    @Test
    void saveTest() throws Exception {
        given(categoryService.save(any())).will(
                (invocation) -> {
                    CategoryDto categoryDto = invocation.getArgument(0);

                    if (categoryDto == null) {
                        return null;
                    }

                    return ManufacturerDto.builder()
                            .id(categoryDto.getId())
                            .name(categoryDto.getTitle())
                            .build();
                });

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"title\": \"test\"}"))
                .andExpect(status().isCreated());
    }
}