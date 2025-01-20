package org.vdt.productmanagementservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.vdt.productmanagementservice.entities.Category;
import org.vdt.productmanagementservice.repositories.CategoryRepository;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class CategoryRepositoryTest {
    @MockitoBean
    private CategoryRepository categoryRepository;

    @Test
    public void TestSaveCategory(){
        Category category = new Category();
        category.setName("Test");
        category.setIsDelete(false);
        category.setDescription(new String("ksjdj").getBytes(StandardCharsets.UTF_8));
        category.setUrlImage("https://www.google.com");
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Assertions.assertNotNull(categoryRepository.save(category));
    }
    @Test
    public void TestGetCategoryById(){
        Category category = new Category();
        category.setName("Test");
        category.setIsDelete(false);
        category.setDescription(new String("ksjdj").getBytes(StandardCharsets.UTF_8));
        category.setUrlImage("https://www.google.com");
        category.setId(UUID.randomUUID().toString());
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        categoryRepository.save(category);
        Category categorySearchById = categoryRepository.findById(category.getId()).get();
        Assertions.assertEquals(category.getId(), categorySearchById.getId());
        Assertions.assertEquals(category.getName(), categorySearchById.getName());
    }
}
