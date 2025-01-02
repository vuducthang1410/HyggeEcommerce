package org.vdt.productmanagementservice.handles;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.multipart.MultipartFile;
import org.vdt.productmanagementservice.common.Constant;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.services.ICategoryService;

@Service
@RequiredArgsConstructor
public class CategoryHandle {
    private final ICategoryService categoryService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public ApiResponseWrapper<Object> addCategory(CategoryRqDto categoryRqDto,
                                                  MultipartFile imageCategory,
                                                  BindingResult bindingResult,
                                                  String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        try {
            if (bindingResult.hasErrors()) {
                apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
                apiResponseWrapper.setMessage("Validation failed for the product creation request.");
                return apiResponseWrapper;
            }
            // handle image to get link
            String urlImage=imageCategory.getOriginalFilename();
            categoryService.addCategory(categoryRqDto,urlImage,transactionId);
            apiResponseWrapper.setCode(HttpStatus.CREATED.value());
            apiResponseWrapper.setMessage("Category added successfully.");
        }catch (Exception e) {
            log.error(Constant.MESSAGE_LOG
                    ,transactionId,"An error occurred while handle for creating new category",e.getMessage()
            );
            apiResponseWrapper.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseWrapper.setMessage("An error occurred while handle for creating new category");
        }
        return apiResponseWrapper;
    }
}
