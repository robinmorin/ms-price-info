package org.test.capitole.domain.ports.out;

import org.test.capitole.domain.model.PriceModel;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Port for get result price from repository search
 */
public interface SearchPriceOutputPort {
     Optional<PriceModel> searchByMostPriority(Long productId, Integer brandId, LocalDateTime effectiveDate);

}
