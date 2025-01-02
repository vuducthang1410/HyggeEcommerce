package org.vdt.productmanagementservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vdt.productmanagementservice.dtos.request.ProductRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.entities.Product;

@RestController
public class ProductController {
    @Operation(summary = "get mapping method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/hello")
    public String helloWorld(@RequestParam(name = "name") String name) {
        return "Hello " + name;
    }

    @PostMapping("/create-product")
    public ResponseEntity<ApiResponseWrapper<Object>> createProduct(@RequestBody ProductRqDto products) {
        return null;
    }
}
