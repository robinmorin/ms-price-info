package org.test.capitole.application.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class PriceResponseTest {

    private PriceResponse priceResponse;

    @Test
    void testGetPriceToApply() {

        // Arrange
        PriceResponse.PriceResponseBuilder brandIdBuilder = PriceResponse.builder().brandId(1);
        PriceResponse.EffectiveDateRange.EffectiveDateRangeBuilder dateRangeBuilder = PriceResponse.EffectiveDateRange.builder();
        PriceResponse.EffectiveDateRange.EffectiveDateRangeBuilder fromDateRangeBuilder = dateRangeBuilder.from(LocalDateTime.now());
        PriceResponse.EffectiveDateRange effectiveDateRange = fromDateRangeBuilder.to(LocalDateTime.now()).build();
        PriceResponse.PriceResponseBuilder priceListIdBuilder = brandIdBuilder.effectiveDateRange(effectiveDateRange).priceListId(1);
        PriceResponse buildResult = priceListIdBuilder.priceToApply(new BigDecimal("22.30")).productId(1L).build();

        // Act
        PriceResponse response = PriceResponse.builder().brandId(1).effectiveDateRange(effectiveDateRange).priceListId(1).priceToApply(new BigDecimal("22.30")).productId(1L).build();

        // Assert
        assertEquals(buildResult.toString(), response.toString());
    }

    @Test
    void testGetPriceToApplyBuildResponseSet() {

        // Arrange
        PriceResponse.PriceResponseBuilder brandIdBuilder = PriceResponse.builder().brandId(1);
        PriceResponse.EffectiveDateRange.EffectiveDateRangeBuilder dateRangeBuilder = PriceResponse.EffectiveDateRange.builder();
        PriceResponse.EffectiveDateRange.EffectiveDateRangeBuilder fromDateRangeBuilder = dateRangeBuilder.from(LocalDateTime.now());
        PriceResponse.EffectiveDateRange effectiveDateRange = fromDateRangeBuilder.to(LocalDateTime.now()).build();
        PriceResponse.PriceResponseBuilder priceListIdBuilder = brandIdBuilder.effectiveDateRange(effectiveDateRange).priceListId(1);
        PriceResponse buildResult = priceListIdBuilder.priceToApply(new BigDecimal("22.30")).productId(1L).build();

        // Act
        PriceResponse response = new PriceResponse();
        response.setBrandId(1);
        response.setProductId(1L);
        response.setPriceListId(1);
        response.setEffectiveDateRange(effectiveDateRange);
        response.setPriceToApply(new BigDecimal("22.30"));

        // Assert
        assertEquals(buildResult.toString(), response.toString());
        assertNotSame("", dateRangeBuilder.toString());
        assertNotSame("", priceListIdBuilder.toString());
    }

}
