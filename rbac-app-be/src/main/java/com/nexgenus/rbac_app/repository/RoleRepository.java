package com.nexgenus.rbac_app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nexgenus.rbac_app.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}