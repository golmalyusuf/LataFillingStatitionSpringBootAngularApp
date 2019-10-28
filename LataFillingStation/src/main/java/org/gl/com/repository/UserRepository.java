package org.gl.com.repository;

import org.gl.com.entity.Permission;
import org.gl.com.entity.Role;
import org.gl.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
}
