package org.test.capitole.domain.usecase;

import org.test.capitole.domain.model.PriceModel;
import org.test.capitole.domain.ports.in.SearchPriceInputPort;
import org.test.capitole.domain.ports.out.SearchPriceOutputPort;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This class is the interactor that interact with the gateway (port) to search the price by most priority
 */
public class SearchPriceUseCase implements SearchPriceInputPort {

    private final SearchPriceOutputPort searchPriceOutputPort;

    public SearchPriceUseCase(SearchPriceOutputPort searchPriceOutputPort) {
        this.searchPriceOutputPort = searchPriceOutputPort;
    }

    public Optional<PriceModel> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate) {
        return searchPriceOutputPort.searchByMostPriority(productId, brandId, effectiveDate);
    }

}
