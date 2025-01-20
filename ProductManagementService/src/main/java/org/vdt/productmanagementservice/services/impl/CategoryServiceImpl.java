package org.vdt.productmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.vdt.productmanagementservice.common.Constant;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.dtos.request.CategoryUpdateRqDto;
import org.vdt.productmanagementservice.dtos.response.CategoryRpDto;
import org.vdt.productmanagementservice.entities.Category;
import org.vdt.productmanagementservice.repositories.CategoryRepository;
import org.vdt.productmanagementservice.services.ICategoryService;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final Logger logger = LoggerFactory
            .getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;
    private final S3Service s3Service;

    @Transactional
    @Override
    public Category addCategory(CategoryRqDto categoryRqDto, MultipartFile image, String transactionId) throws Exception {
        Category category = new Category();
        category.setName(categoryRqDto.getCategoryName());
        if(categoryRqDto.getCategoryParentId() != null&& StringUtils.hasText(categoryRqDto.getCategoryParentId())) {
            Optional<Category> parentOptional = categoryRepository.findById(categoryRqDto.getCategoryParentId());
            if(parentOptional.isPresent()) {
                category.setParentId(categoryRqDto.getCategoryParentId());
            }else{
                return null;
            }
        }

        String urlImage = s3Service.uploadFile(image);
        if (urlImage == null) throw new Exception();
        category.setUrlImage(urlImage);
        category.setDescription(categoryRqDto.getDescription().getBytes(StandardCharsets.UTF_8));
        category.setIsDelete(Constant.Status.N);
        return categoryRepository.save(category);
    }

    @Override
    @Cacheable(value = "categories", key = "#id", unless = "#result == null")
    public CategoryRpDto findById(String id, String transactionId) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return convertToCategoryRpDto(category);
        }
        return null;
    }

    @Override
    @CacheEvict(value = "categories", key = "#id")
    public Boolean deleteCategoryById(String id, String transactionId) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(id);
            s3Service.deleteFile(categoryOptional.get().getUrlImage());
            return true;
        } else
            logger.warn("Category with id {} not found for deletion {}", id, transactionId);
        return false;
    }

    @Override
    public Map<String, Object> findAllCategories(Integer pageNo, Integer pageSize, String transactionId) {
        Map<String, Object> dataResponse = new HashMap<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "createdBy"));
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryRpDto> categoryRpDtoList = categoryPage
                .getContent()
                .stream()
                .map(this::convertToCategoryRpDto)
                .toList();
        dataResponse.put("categoryRpDtoList", categoryRpDtoList);
        dataResponse.put("totalRecords", categoryPage.getTotalElements());
        return dataResponse;
    }

    @Override
    @CachePut(value = "categories", key = "#id")
    public CategoryRpDto updateCategory(CategoryUpdateRqDto categoryUpdateRqDto, String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return null;
        }
        Category category = categoryOptional.get();
        category.setName(categoryUpdateRqDto.getCategoryName());
        category.setDescription(categoryUpdateRqDto.getDescription().getBytes(StandardCharsets.UTF_8));
        category.setParentId(categoryUpdateRqDto.getParentId());
        categoryRepository.save(category);
        return convertToCategoryRpDto(category);
    }

    private CategoryRpDto convertToCategoryRpDto(Category category) {
        CategoryRpDto categoryRqDto = new CategoryRpDto();
        categoryRqDto.setCategoryName(category.getName());
        categoryRqDto.setParentId(category.getParentId());
        categoryRqDto.setCategoryImage(s3Service.getLinkFile(category.getUrlImage()));
        if (category.getDescription() != null)
            categoryRqDto.setDescription(new String(category.getDescription(), StandardCharsets.UTF_8));
        categoryRqDto.setCategoryId(category.getId());
        return categoryRqDto;
    }
}
