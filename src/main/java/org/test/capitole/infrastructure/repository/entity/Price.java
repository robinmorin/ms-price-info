package org.test.capitole.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class Price implements Serializable {

    private static final long serialVersionUID = 7480742869731054793L;

    @EmbeddedId
    private PricePK pricePK;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRIORITY", nullable = false)
    private Short priority;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal vPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ISO", referencedColumnName = "CURRENCY_ISO")
    private Currency currency;

}
