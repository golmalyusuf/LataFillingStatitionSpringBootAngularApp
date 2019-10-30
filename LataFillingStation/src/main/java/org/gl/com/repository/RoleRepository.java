package org.gl.com.repository;

import org.gl.com.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value = "select * from roles e",nativeQuery = true) 
    Page<Role> findAllRolesPagination(Pageable pageable);
  
}
