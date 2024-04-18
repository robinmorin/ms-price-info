package org.test.capitole.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.test.capitole.Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static final Long PRODUCT_ID = 35455L;
    private static final Integer BRAND_ID = 1;
    private final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products/{productId}/brand/{brandId}/price-to-apply", PRODUCT_ID, BRAND_ID);

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void integrationTest1_ShouldReturnSuccessWithPriceDetails() throws Exception {
        String expectedJson = """
                                {
                                  "productId": 35455,
                                  "brandId": 1,
                                  "priceListId": 1,
                                  "effectiveDateRange": {
                                    "from": "2020-06-14 00:00:00",
                                    "to": "2020-12-31 23:59:59"
                                  },
                                  "priceToApply": 35.50
                                }
                              """;
        callEndpoint("2020-06-14 10:00:00", expectedJson);
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void integrationTest2_ShouldReturnSuccessWithPriceDetails() throws Exception {
        String expectedJson = """
                                {
                                  "productId": 35455,
                                  "brandId": 1,
                                  "priceListId": 2,
                                  "effectiveDateRange": {
                                    "from": "2020-06-14 15:00:00",
                                    "to": "2020-06-14 18:30:00"
                                  },
                                  "priceToApply": 25.45
                                }
                              """;
        callEndpoint("2020-06-14 16:00:00", expectedJson);
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void integrationTest3_ShouldReturnSuccessWithPriceDetails() throws Exception {
        String expectedJson = """
                                {
                                  "productId": 35455,
                                  "brandId": 1,
                                  "priceListId": 1,
                                  "effectiveDateRange": {
                                    "from": "2020-06-14 00:00:00",
                                    "to": "2020-12-31 23:59:59"
                                  },
                                  "priceToApply": 35.50
                                }
                              """;
        callEndpoint("2020-06-14 21:00:00", expectedJson);
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    void integrationTest4_ShouldReturnSuccessWithPriceDetails() throws Exception {
        String expectedJson = """
                                {
                                  "productId": 35455,
                                  "brandId": 1,
                                  "priceListId": 3,
                                  "effectiveDateRange": {
                                    "from": "2020-06-15 00:00:00",
                                    "to": "2020-06-15 11:00:00"
                                  },
                                  "priceToApply": 30.50
                                }
                              """;
        callEndpoint("2020-06-15 10:00:00", expectedJson);
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void integrationTest5_ShouldReturnSuccessWithPriceDetails() throws Exception {
        String expectedJson = """
                                {
                                  "productId": 35455,
                                  "brandId": 1,
                                  "priceListId": 4,
                                  "effectiveDateRange": {
                                    "from": "2020-06-15 16:00:00",
                                    "to": "2020-12-31 23:59:59"
                                  },
                                  "priceToApply": 38.95
                                }
                              """;
        callEndpoint("2020-06-16 21:00:00", expectedJson);
    }

    void callEndpoint(String effectiveDate, String expectedResponse) throws Exception {
         mockMvc.perform(requestBuilder.param("effectiveDate", effectiveDate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
    }

    @Test
    @DisplayName("Test de Error: petición para el dia de hoy con el producto 35455 para la brand 1 (ZARA)")
    void integrationTestFail_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(requestBuilder.param("effectiveDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .andExpect(status().isNotFound());
    }
}
