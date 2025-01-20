package org.vdt.productmanagementservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.dtos.request.CategoryUpdateRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.dtos.response.CategoryRpDto;
import org.vdt.productmanagementservice.handlers.CategoryHandler;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryHandler categoryHandle;

    @Operation(summary = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "created new product successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Validation failed for the product creation request.",
                    content = @Content),
    })
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponseWrapper<Object>> createCategory(
            @Parameter(description = "Category request DTO")
            @Valid @RequestPart(name = "categoryRqDto") CategoryRqDto categoryRqDto,
            @Parameter(description = "file image of category")
            @RequestPart(name = "image") MultipartFile image,
            @Parameter(description = "transaction id of request")
            @RequestHeader(name = "transactionId") String transactionId,
            BindingResult bindingResult) {
        return new ResponseEntity<>(
                categoryHandle.addCategory(
                        categoryRqDto, image, bindingResult, transactionId)
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get category successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryRpDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Validation failed for the product creation request.",
                    content = @Content),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponseWrapper<Object>> getCategoryById(
            @PathVariable("id") String id,
            @RequestHeader(name = "transactionId") String transactionId) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseEntity<>(
                categoryHandle.findByIdHandle(id, transactionId),
                HttpStatus.OK);
    }

    @Operation(summary = "Delete category by Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseWrapper<Object>> deleteCategory(
            @Parameter(description = "id category") @PathVariable("id") String id,
            @RequestHeader String transactionId
    ) {
        return new ResponseEntity<>(
                categoryHandle.deleteById(id, transactionId),
                HttpStatus.OK);
    }

    @Operation(summary = "get all category by page no and page size")
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseWrapper<Object>> getAllCategories(
            @Parameter(example = "0", description = "page number")
            @RequestParam(name = "pageNo", defaultValue = "0",required = false) Integer pageNo,
            @Parameter(example = "0", description = "page size")
            @RequestParam(name = "pageSize", defaultValue = "12",required = false) Integer pageSize,
            @RequestHeader String transactionId
    ) {
        return new ResponseEntity<>(
                categoryHandle.getAllCategory(pageNo, pageSize, transactionId),
                HttpStatus.OK);
    }
    @Operation(summary = "update category by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseWrapper<Object>> updateCategory(
            @PathVariable(name = "id") String id,
            @RequestBody @Valid CategoryUpdateRqDto categoryUpdateRqDto,
            @RequestHeader String transactionId,
            BindingResult bindingResult
            ){
        return new ResponseEntity<>(
                categoryHandle.handlerUpdate(id,categoryUpdateRqDto,transactionId,bindingResult),
                HttpStatus.OK);
    }
}
