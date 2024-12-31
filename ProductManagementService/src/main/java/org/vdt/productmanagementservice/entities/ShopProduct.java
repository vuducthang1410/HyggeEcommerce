package org.vdt.productmanagementservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ShopProduct extends BaseEntity {
    @Column(length = 50, nullable = false, name = "PRODUCT_ID")
    private String productId;
    @Column(length = 50, nullable = false, name = "SHOP_ID")
    private String shopId;
    @Column(length = 12, nullable = false, name = "PRICE")
    @Max(999999999)
    @Min(0)
    private BigDecimal price;
}
