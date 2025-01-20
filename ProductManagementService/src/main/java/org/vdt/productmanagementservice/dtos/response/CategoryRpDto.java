package org.vdt.productmanagementservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRpDto implements Serializable {
    private String categoryId;
    private String categoryName;
    private String description;
    private String categoryImage;
    private String parentId;
}
