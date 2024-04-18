package org.test.capitole.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.capitole.domain.model.PriceModel;
import org.test.capitole.domain.ports.out.SearchPriceOutputPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchPriceUseCaseTest {

    @Mock
    private SearchPriceOutputPort searchPriceOutputPort;

    @InjectMocks
    private SearchPriceUseCase searchPriceUseCase;

    @Test
    void testNewSearchPriceUseCase() {
        // Arrange and Act
        Assertions.assertDoesNotThrow(()-> new SearchPriceUseCase(searchPriceOutputPort));
    }

    @Test
    void testSearchByMostPriority() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(4);
        LocalDateTime endDate = LocalDateTime.now();
        PriceModel.RangeDate effectiveDates = new PriceModel.RangeDate(startDate, endDate);

        Optional<PriceModel> ofResult = Optional.of(PriceModel.builder()
                                                    .brandId(1)
                                                    .productId(1L)
                                                    .priceList(1)
                                                    .priority((short) 1)
                                                    .effectiveDates(effectiveDates)
                                                    .price(new BigDecimal("22.30"))
                                                    .currencyIso("EUR")
                                                .build());
        when(searchPriceOutputPort.searchByMostPriority(anyLong(), anyInt(), any(LocalDateTime.class)))
            .thenReturn(ofResult);

        // Act
        Optional<PriceModel> actualSearchByMostPriorityResult = searchPriceUseCase
                                                                .searchByMostPriority(1L, 1, LocalDateTime.now().minusHours(2));

        // Assert
        verify(searchPriceOutputPort).searchByMostPriority(anyLong(), anyInt(), any(LocalDateTime.class));
        PriceModel.RangeDate effectiveDates2 = actualSearchByMostPriorityResult.get().getEffectiveDates();
        assertSame(effectiveDates, effectiveDates2);
    }
}
