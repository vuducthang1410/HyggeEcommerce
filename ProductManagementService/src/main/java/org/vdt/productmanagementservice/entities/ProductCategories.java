package org.vdt.productmanagementservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_product_categories")
public class ProductCategories extends BaseEntity{
    @Column(length = 100, nullable = false,name = "PRODUCT_ID")
    private String productId;
    @Column(length = 100, nullable = false,name = "CATEGORY_ID")
    private String categoryId;
}
