package org.test.capitole.infrastructure.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

/**
 * Brand entity
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BRAND")
public class Brand implements Serializable {

    private static final long serialVersionUID = -8866512402807353987L;

    @Id
    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;

    @Column(name = "BRAND_NAME", nullable = false)
    private String brandName;

}
