package org.vdt.productmanagementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdt.productmanagementservice.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
