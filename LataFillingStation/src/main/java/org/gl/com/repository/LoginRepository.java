package org.gl.com.repository;

import org.gl.com.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {

}
