package org.vdt.productmanagementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_product_variant_attributes")
public class ProductVariantAttributes extends BaseEntity {
}
