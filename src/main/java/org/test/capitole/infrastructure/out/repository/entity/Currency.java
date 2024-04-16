package org.test.capitole.infrastructure.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

/**
 *
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    private static final long serialVersionUID = -69651175129566982L;

    @Id
    @Column(name = "CURRENCY_ISO", nullable = false)
    private String currencyIso;

    @Column(name = "CURRENCY_NAME", nullable = false)
    private String currencyName;

}
