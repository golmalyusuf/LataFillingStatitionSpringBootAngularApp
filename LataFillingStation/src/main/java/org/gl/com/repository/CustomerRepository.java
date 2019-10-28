package org.gl.com.repository;

import org.gl.com.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByCustomerName(String customerName, Pageable pageable);

}
