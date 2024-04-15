package org.test.capitole.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/***
 * PriceDomain class: Represent the price domain object without any dependency of neither framework nor infrastructure. Implement the builder pattern.
 */
public class PriceModel {

    private Integer brandId;

    private Long productId;

    private Integer priceList;

    private RangeDate effectiveDates;

    private Short priority;

    private BigDecimal price;

    private String currencyIso;

    public PriceModel(Integer brandId, Long productId, Integer priceList, RangeDate effectiveDates, Short priority, BigDecimal price, String currencyIso) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.effectiveDates = effectiveDates;
        this.priority = priority;
        this.price = price;
        this.currencyIso = currencyIso;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public RangeDate getEffectiveDates() {
        return effectiveDates;
    }
    public Short getPriority() {
        return priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public static class RangeDate {
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public RangeDate(LocalDateTime startDate, LocalDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }
    }

    public static PricesDomainBuilder builder() {
        return new PricesDomainBuilder();
    }

    public static class PricesDomainBuilder {
        private Integer brandId;
        private Long productId;
        private Integer priceList;
        private RangeDate effectiveDates;
        private Short priority;
        private BigDecimal price;
        private String currencyIso;

        public PricesDomainBuilder brandId(Integer brandId) {
            this.brandId = brandId;
            return this;
        }

        public PricesDomainBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public PricesDomainBuilder priceList(Integer priceList) {
            this.priceList = priceList;
            return this;
        }

        public PricesDomainBuilder effectiveDates(RangeDate effectiveDates) {
            this.effectiveDates = effectiveDates;
            return this;
        }

        public PricesDomainBuilder priority(Short priority) {
            this.priority = priority;
            return this;
        }

        public PricesDomainBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public PricesDomainBuilder currencyIso(String currencyIso) {
            this.currencyIso = currencyIso;
            return this;
        }

        public PriceModel build() {
            return new PriceModel(brandId, productId, priceList, effectiveDates, priority, price, currencyIso);
        }

    }


}
