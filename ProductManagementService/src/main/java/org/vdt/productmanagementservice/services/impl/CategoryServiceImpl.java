package org.vdt.productmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vdt.productmanagementservice.common.Constant;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.entities.Category;
import org.vdt.productmanagementservice.repositories.CategoryRepository;
import org.vdt.productmanagementservice.services.ICategoryService;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;
    @Transactional
    @Override
    public String addCategory(CategoryRqDto categoryRqDto,String urlImage,String transactionId) {
        try{
            Category category = new Category();
            category.setName(categoryRqDto.getCategoryName());
            category.setParentId(categoryRqDto.getCategoryParentId());
            category.setUrlImage(urlImage);
            categoryRepository.save(category);
        } catch (Exception e) {
            logger.error(Constant.MESSAGE_LOG,
                    transactionId,"An error occurred while creating a new product",e.getMessage()
            );
            return "An error occurred while creating a new product";
        }
        return "Created new category successful";
    }
}
