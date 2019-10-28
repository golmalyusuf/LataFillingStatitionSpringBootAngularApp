package org.gl.com.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.gl.com.entity.Expense;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/lata")
public class ExpenseController {
	@Autowired
	public ExpenseRepository expenseRepository; 
	
	 
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(){
		return (List<Expense>) expenseRepository.findAll();
	} 
	
	@GetMapping("/expenses/{id}")
	public Optional<Expense> getExpense(@PathVariable Integer id){
		return expenseRepository.findById(id);
	}
	
	@GetMapping("/expensesbydate/strDate={strDate}&endDate={endDate}")
	public List<Expense> getExpenseByDates(@PathVariable String strDate, @PathVariable String endDate){
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		Date strDateTime = new Date();
		Date endDateTime = new Date();
		try {
			strDateTime = format.parse(strDate);
			endDateTime = format.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expenseRepository.findAllWithinDates(strDateTime, endDateTime);
	}

	@RequestMapping(value = "/expensesbydate/get", params = { "page", "size", "strDate",
			"endDate" }, method = RequestMethod.GET)
	public Page<Expense> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam String strDate, @RequestParam String endDate) {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Date strDateTime = new Date();
		Date endDateTime = new Date();
		try {
			strDateTime = format.parse(strDate);
			endDateTime = format.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Page<Expense> resultPage = expenseRepository.findAllWithinDatesPagination(strDateTime, endDateTime, PageRequest.of(page, size));
		if (page > resultPage.getTotalPages()) {
			System.out.println("Resource Not Found");
		}

		return resultPage;
	}

	@PostMapping("/save-expenses")
	public ResponseEntity<Void> createExpense(@RequestBody Expense expense){
		expense.setModified_By("yusuf");
		Expense expenseCreated =  expenseRepository.save(expense);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(expenseCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-expenses/{id}")
	public ResponseEntity<Expense> editExpense(@PathVariable long id, @RequestBody Expense expense){
		System.out.println("expnese des"+expense.getModified_By());
		Expense expenseUpdated = expenseRepository.save(expense);
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}
	
	@GetMapping("/delete-expenses/{id}")
	   public void deleteExpense(@PathVariable(value = "id") Integer id) throws Exception {
	        
		 expenseRepository.findById(id).map(expense-> {
			 expenseRepository.delete(expense);
			 return ResponseEntity.ok().build();
		 }).orElseThrow(()-> new ResourceNotFoundException("ExpenseId "+id+" not found"));
	   }
	
	@RequestMapping(value = "/calculateexpensesbydate/get", params = {"strDate",
	"endDate" }, method = RequestMethod.GET)
	public BigDecimal CalculateAllWithinDatesPagination(@RequestParam String strDate, @RequestParam String endDate) {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
 
		Date strDateTime = new Date();
		Date endDateTime = new Date();
		try {
			strDateTime = format.parse(strDate);
			endDateTime = format.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 	
		BigDecimal total = expenseRepository.CalculateAllWithinDates(strDateTime, endDateTime);
		 
		return total;
	}
}
