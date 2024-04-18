package org.test.capitole.application.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.test.capitole.application.dto.PriceResponse;
import org.test.capitole.domain.model.PriceModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceResponseMapperTest {

    PriceResponseMapper priceResponseMapper = Mappers.getMapper(PriceResponseMapper.class);

    @Test
    void testToResponseSuccess() {
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
        PriceResponse actualResponse = priceResponseMapper.toResponse(priceModel);

        // Assert
        PriceResponse.EffectiveDateRange effectiveDateRange = actualResponse.getEffectiveDateRange();
        assertEquals(startDate, effectiveDateRange.getFrom());
        assertEquals(endDate, effectiveDateRange.getTo());
        assertEquals(1, actualResponse.getBrandId().intValue());
        assertEquals(1, actualResponse.getPriceListId().intValue());
        assertEquals(1L, actualResponse.getProductId().longValue());
        assertEquals(new BigDecimal("22.30"), actualResponse.getPriceToApply());
    }

    @Test
    void testToResponseNull(){
        // Act
        PriceResponse actualResponse = priceResponseMapper.toResponse(null);
        // Assert
        assertNull(actualResponse);
    }

}