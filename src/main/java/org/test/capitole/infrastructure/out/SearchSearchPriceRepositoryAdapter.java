package org.test.capitole.infrastructure.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test.capitole.domain.model.PriceModel;
import org.test.capitole.domain.ports.out.SearchPriceOutputPort;
import org.test.capitole.infrastructure.out.repository.PriceRepository;
import org.test.capitole.infrastructure.out.repository.mapper.PriceEntityMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;


/**
 * SearchSearchPriceRepositoryAdapter class : Represents the adapter that uses the PriceRepository to search for prices in the database.
 */
@Slf4j
@RequiredArgsConstructor
@Component
@SuppressWarnings("java:S3864")
public class SearchSearchPriceRepositoryAdapter implements SearchPriceOutputPort {

    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    public Optional<PriceModel> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        log.info("Searching in <Repository> the price for Product: {} Brand: {} EffectiveDate: {}", productId, brandId, effectiveDate);
        return priceRepository.findByParamsOrderPriorityDesc(productId, brandId, effectiveDate)
                                .stream()
                                .flatMap(Collection::stream)
                                .peek(price -> log.info("Price found in <Repository> - Price List: {}, Priority: {}, Value: {}, Currency: {}",
                                                         price.getPricePK().getPriceList(), price.getPriority(), price.getVPrice(),
                                                         price.getCurrency().getCurrencyIso()))
                                .findFirst()
                                .map(priceEntityMapper::toDomain);
    }
}
