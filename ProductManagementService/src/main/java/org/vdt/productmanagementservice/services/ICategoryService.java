package org.vdt.productmanagementservice.services;

import org.springframework.web.multipart.MultipartFile;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.dtos.request.CategoryUpdateRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.dtos.response.CategoryRpDto;
import org.vdt.productmanagementservice.entities.Category;

import java.util.Map;

public interface ICategoryService{
    Category addCategory(CategoryRqDto categoryRqDto, MultipartFile image, String transactionId) throws Exception;

    CategoryRpDto findById(String id,String transactionId);
    Boolean deleteCategoryById(String id,String transactionId);
    Map<String, Object> findAllCategories(Integer pageNo, Integer pageSize, String transactionId);

    CategoryRpDto updateCategory(CategoryUpdateRqDto categoryUpdateRqDto, String id);
}
