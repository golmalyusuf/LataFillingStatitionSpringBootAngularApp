package org.gl.com.repository;

import org.gl.com.entity.CustomerProperties;
import org.gl.com.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPropertyRepository extends JpaRepository<CustomerProperties, Integer> {

}
