package org.test.capitole.infrastructure.in.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.test.capitole.domain.model.PriceModel;
import org.test.capitole.infrastructure.in.dto.PriceResponse;


/**
 * Mapper to convert domain object PriceDomain to presentation object PriceResponse
 */
@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(target = "priceListId", source = "priceList")
    @Mapping(target = "priceToApply", source = "price", numberFormat = "$#.00")
    @Mapping(target = "effectiveDateRange.from", source = "effectiveDates.startDate")
    @Mapping(target = "effectiveDateRange.to", source = "effectiveDates.endDate")
    PriceResponse toResponse(PriceModel priceModel);

}
