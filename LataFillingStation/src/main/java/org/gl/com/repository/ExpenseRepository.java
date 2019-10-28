package org.gl.com.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.gl.com.entity.Customer;
import org.gl.com.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {
	 
    @Query(value = "select * from expense e where e.expense_date between ?1 and ?2",nativeQuery = true) 
    List<Expense> findAllWithinDates(@Param("expense_date") Date strDateTime, @Param("expense_date") Date endDateTime);
    
    @Query(value = "select * from expense e where e.expense_date between ?1 and ?2",nativeQuery = true) 
    Page<Expense> findAllWithinDatesPagination(@Param("expense_date") Date strDateTime, @Param("expense_date") Date endDateTime, Pageable pageable);

	/*
	 * @Query(value =
	 * "select SUM(EXPENSE_AMOUNT) from expense e where e.expense_date between ?1 and ?2"
	 * ,nativeQuery = true) Double CalculateAllWithinDates(@Param("expense_date")
	 * Date strDateTime, @Param("expense_date") Date endDateTime);
	 */
    @Query(value = "select SUM(EXPENSE_AMOUNT) from expense e where e.expense_date between ?1 and ?2",nativeQuery = true) 
    BigDecimal CalculateAllWithinDates(@Param("expense_date") Date strDateTime, @Param("expense_date") Date endDateTime);
    
}
