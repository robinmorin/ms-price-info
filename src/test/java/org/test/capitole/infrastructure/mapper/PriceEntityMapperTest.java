package org.test.capitole.infrastructure.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.test.capitole.domain.model.PriceModel;
import org.test.capitole.infrastructure.repository.entity.*;
import org.test.capitole.infrastructure.repository.mapper.PriceEntityMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceEntityMapperTest {

    PriceEntityMapper priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    void testToEntity() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(4);
        LocalDateTime endDate = LocalDateTime.now();
        PriceModel.RangeDate effectiveDates = new PriceModel.RangeDate(startDate, endDate);
        var priceModel = PriceModel.builder()
                                    .brandId(1)
                                    .productId(1L)
                                    .priceList(1)
                                    .priority((short) 1)
                                    .effectiveDates(effectiveDates)
                                    .price(new BigDecimal("22.30"))
                                    .currencyIso("EUR")
                                .build();
        // Act
        Price actualResult = priceEntityMapper.toEntity(priceModel);

        // Assert
        assertEquals(startDate, actualResult.getStartDate());
        assertEquals(endDate, actualResult.getEndDate());
        Currency currency = actualResult.getCurrency();
        assertEquals("EUR", currency.getCurrencyIso());
        PricePK pricePK = actualResult.getPricePK();
        Brand brand = pricePK.getBrand();
        assertNull(brand.getBrandName());
        assertNull(currency.getCurrencyName());
        Product product = pricePK.getProduct();
        assertNull(product.getProductName());
        assertNull(product.getReferencia());
        assertNull(product.getSku());
        assertEquals(new BigDecimal("22.30"), actualResult.getVPrice());
        assertEquals(1, brand.getBrandId().intValue());
        assertEquals(1, pricePK.getPriceList().intValue());
        assertEquals(1L, product.getProductId().longValue());
        assertEquals((short) 1, actualResult.getPriority().shortValue());
    }

    @Test
    void testToEntityNull() {
        // Act
        Price actualResult = priceEntityMapper.toEntity(null);

        // Assert
        assertNull(actualResult);
    }

    @Test
    void testToDomain() {
        // Arrange
        Currency currency = new Currency();
        currency.setCurrencyIso("EUR");
        currency.setCurrencyName("Euros");

        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName("Zara");

        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Varios");
        product.setReferencia("Referencia");
        product.setSku("Sku");

        PricePK pricePK = new PricePK();
        pricePK.setBrand(brand);
        pricePK.setPriceList(1);
        pricePK.setProduct(product);

        Price price = new Price();
        price.setPricePK(pricePK);
        price.setEndDate(LocalDateTime.now().plusDays(2));
        price.setStartDate(LocalDateTime.now().minusDays(1));
        price.setPriority((short) 1);
        price.setVPrice(new BigDecimal("22.30"));
        price.setCurrency(currency);

        // Act
        PriceModel result = priceEntityMapper.toDomain(price);
        PriceModel.RangeDate effectiveDates = result.getEffectiveDates();

        // Assert
        assertSame(price.getPricePK().getBrand().getBrandId(), result.getBrandId());
        assertSame(price.getPricePK().getProduct().getProductId(), result.getProductId());
        assertSame(price.getPricePK().getPriceList(), result.getPriceList());
        assertSame(price.getPriority(), result.getPriority());
        assertSame(price.getVPrice(), result.getPrice());
        assertSame(price.getStartDate(), effectiveDates.getStartDate());
        assertSame(price.getEndDate(), effectiveDates.getEndDate());
        assertSame(price.getCurrency().getCurrencyIso(), result.getCurrencyIso());
    }

    @Test
    void testToDomainNull() {
        // Act
        PriceModel actualResult = priceEntityMapper.toDomain(null);

        // Assert
        assertNull(actualResult);
    }

}
