package org.vdt.productmanagementservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRqDto {
    private String categoryName;
    private String description;
    private String categoryParentId;
}
