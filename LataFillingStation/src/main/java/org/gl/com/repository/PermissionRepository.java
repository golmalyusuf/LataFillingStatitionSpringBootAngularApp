package org.gl.com.repository;

import org.gl.com.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
