package org.vdt.productmanagementservice.services;

import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;

public interface ICategoryService{
    String addCategory(CategoryRqDto categoryRqDto, String urlImage, String transactionId);
}
