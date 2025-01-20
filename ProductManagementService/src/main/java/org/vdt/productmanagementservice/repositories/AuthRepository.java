package org.vdt.productmanagementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdt.productmanagementservice.entities.User;

public interface AuthRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
