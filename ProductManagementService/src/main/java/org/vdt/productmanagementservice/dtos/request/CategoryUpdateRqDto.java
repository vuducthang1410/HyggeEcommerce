package org.vdt.productmanagementservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRqDto {
    @NotBlank
    private String categoryName;
    @NotBlank
    private String description;
    private String parentId;
}
