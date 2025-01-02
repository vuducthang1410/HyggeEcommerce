package org.vdt.productmanagementservice.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "product dto for create new product")
public class ProductRqDto {
    @NotNull
    @Schema(description = "name of new product",
            example = "Skirt")
    private String nameProduct;
    @Schema(
            description = "List of category IDs associated with the product",
            example = "[\"4a57ca3f-8f5f-4962-bc48-afd50d27cfb9\"," +
                    " \"90dff875-3981-461e-a0a6-41127bf9e804\"," +
                    " \"c93b6d6c-8243-4c54-badc-3a1e2d615bf8\"]"
    )
    private List<String> categoryIdList;
    @NotNull
    @Schema(description = "Description of the product",
            example = "This is a sample product description")
    private String description;
    @NotNull
    @Schema(description = "Price of the product", example = "199.99")
    private String price;
    @Schema(description = "List of category IDs the product belongs to",
            example = "[\"4a57ca3f-8f5f-4962-bc48-afd50d27cfb9\"," +
                    " \"90dff875-3981-461e-a0a6-41127bf9e804\"," +
                    " \"c93b6d6c-8243-4c54-badc-3a1e2d615bf8\"]")
    private List<String> attributeIdList;
    @Schema(description = "List of attributes with name and value",
            example = "[{\"attributeName\": \"Color\", \"attributeValue\": \"Red\"}," +
                    " {\"attributeName\": \"Size\", \"attributeValue\": \"M\"}]")
    private List<AttributeRq> attributeRqList;
}
