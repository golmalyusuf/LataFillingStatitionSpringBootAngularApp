package org.gl.com.repository;

import org.gl.com.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	Page<Product> findAll(Pageable pageable);
}
