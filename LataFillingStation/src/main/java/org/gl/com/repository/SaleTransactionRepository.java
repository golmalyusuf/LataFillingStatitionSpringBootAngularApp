package org.gl.com.repository;

import java.util.Date;
import java.util.List;

import org.gl.com.entity.SaleTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Integer> {

	 @Query(value = "select * from sales_transactions s where s.sale_date_time between ?1 and ?2",nativeQuery = true) 
	    List<SaleTransaction> findAllWithinDateTime(@Param("sale_date_time") Date strDateTime, @Param("sale_date_time") Date endDateTime);

	 @Query(value = "select * from sales_transactions e where e.sale_date_time between ?1 and ?2",nativeQuery = true) 
	    Page<SaleTransaction> findAllWithinDatesPagination(@Param("expense_date") Date strDateTime, @Param("expense_date") Date endDateTime, Pageable pageable);
}
