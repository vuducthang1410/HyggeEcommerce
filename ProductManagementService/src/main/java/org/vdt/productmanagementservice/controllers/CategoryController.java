package org.vdt.productmanagementservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.handles.CategoryHandle;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryHandle categoryHandle;

    @Operation(summary = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "created new product successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Validation failed for the product creation request.",
                    content = @Content),
    })
    @PostMapping(value = "/create-category", consumes = {"multipart/form-data" })
    public ResponseEntity<ApiResponseWrapper<Object>> createCategory(
            @RequestPart( name = "categoryRqDto") CategoryRqDto categoryRqDto,
            @RequestPart( name = "image") Mono<FilePart> image,
            @RequestPart(name = "transactionId") String transactionId
            ) {
        return new ResponseEntity<>(
                categoryHandle.addCategory(null, null,null, null)
                , HttpStatus.CREATED);
    }
}
