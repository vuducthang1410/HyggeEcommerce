package org.vdt.productmanagementservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRqDto implements Serializable {
    @NonNull
    private String categoryName;
    @NotBlank
    private String description;
    private String categoryParentId;
}
