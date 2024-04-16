package org.test.capitole.domain.ports.in;

import org.test.capitole.domain.model.PriceModel;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Gateway (port) for connect to repository for search prices
 */
public interface SearchPriceInputPort {
     Optional<PriceModel> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate);

}
