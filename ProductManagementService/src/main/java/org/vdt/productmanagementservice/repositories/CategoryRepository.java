package org.vdt.productmanagementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdt.productmanagementservice.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {

}
