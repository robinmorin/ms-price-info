package org.test.capitole.infrastructure.out.repository.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test.capitole.domain.model.PriceModel;
import org.test.capitole.infrastructure.out.repository.entity.*;

/**
 * PriceMapper class : Mapper to convert domain object PriceDomain to entity object Price
 */
@Slf4j
@Component
public class PriceEntityMapper {
    public Price toEntity(PriceModel priceModel) {
        log.info("Converting Domain Price to Entity Price");
        var product = Product.builder().productId(priceModel.getProductId()).build();
        var brand = Brand.builder().brandId(priceModel.getBrandId()).build();
        var currency = Currency.builder().currencyIso(priceModel.getCurrencyIso()).build();
        var pricePk = PricePK.builder().brand(brand).product(product).priceList(priceModel.getPriceList()).build();
        return Price.builder()
                        .pricePK(pricePk)
                        .priority(priceModel.getPriority())
                        .currency(currency)
                        .startDate(priceModel.getEffectiveDates().getStartDate())
                        .endDate(priceModel.getEffectiveDates().getEndDate())
                        .vPrice(priceModel.getPrice())
                    .build();
    }

    public PriceModel toDomain(Price price) {
        log.info("Converting Entity Price to Domain Price");
        var rangeDate = new PriceModel.RangeDate(price.getStartDate(), price.getEndDate());
        return PriceModel.builder()
                            .productId(price.getPricePK().getProduct().getProductId())
                            .brandId(price.getPricePK().getBrand().getBrandId())
                            .priceList(price.getPricePK().getPriceList())
                            .priority(price.getPriority())
                            .effectiveDates(rangeDate)
                            .price(price.getVPrice())
                            .currencyIso(price.getCurrency().getCurrencyIso())
                        .build();
    }
}
