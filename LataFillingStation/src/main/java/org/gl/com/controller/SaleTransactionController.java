package org.gl.com.controller;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.gl.com.entity.Expense;
import org.gl.com.entity.SaleTransaction;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.SaleTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class SaleTransactionController {
	@Autowired
	public SaleTransactionRepository saleTransactionRepository ; 
	
	@GetMapping("/saleTransactions")
	public List<SaleTransaction> getAllSaleTransactions(){
		return (List<SaleTransaction>) saleTransactionRepository.findAll();
	}
	
    @RequestMapping(value = "/saleTransaction/get", params = { "page", "size"}, method = RequestMethod.GET)
	public Page<SaleTransaction> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<SaleTransaction> resultPage = saleTransactionRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "productName")));
		if (page > resultPage.getTotalPages()) {
			System.out.println("Resource Not Found");
		}
		return resultPage;
	}
    
    @RequestMapping(value = "/saleTransactionsWithDtTm/get", params = { "page", "size", "strDate",
	"endDate" }, method = RequestMethod.GET)
	public Page<SaleTransaction> getAllSaleTransactionsWithDtTm(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam String strDate, @RequestParam String endDate) {
		//return (List<SaleTransaction>) saleTransactionRepository.findAll();
    	DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    	 
		Date strDateTime = new Date();
		Date endDateTime = new Date();
		System.out.println();
		try {
			strDateTime = format.parse(strDate);
			endDateTime = format.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Page<SaleTransaction> resultPage = saleTransactionRepository.findAllWithinDatesPagination(strDateTime, endDateTime, PageRequest.of(page, size));
		if (page > resultPage.getTotalPages()) {
			System.out.println("Resource Not Found");
		}

		return resultPage;
		
	}
	
	@GetMapping("/saleTransaction/{id}")
	public Optional<SaleTransaction> getSaleTransaction(@PathVariable Integer id){
		return saleTransactionRepository.findById(id);
	} 
	
	@PostMapping("/save-saleTransaction")
	@Transactional
	public ResponseEntity<Void> createSaleTransaction(@Valid @RequestBody SaleTransaction saleTransaction){
		SaleTransaction saleTransactionCreated = saleTransactionRepository.save(saleTransaction);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saleTransactionCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-saleTransaction/{id}")
	@Transactional
	public SaleTransaction editProduct(@PathVariable Integer id, @Valid @RequestBody SaleTransaction saleTransaction){
		return saleTransactionRepository.findById(id).map(saleTransactionLocal->{
			saleTransactionLocal.setProductName(saleTransaction.getProductName());
			saleTransactionLocal.setProductRetailPrice(saleTransaction.getProductRetailPrice());
			saleTransactionLocal.setTotalPrice(saleTransaction.getTotalPrice());
			saleTransactionLocal.setPaidPrice(saleTransaction.getPaidPrice());
			saleTransactionLocal.setDuePrice(saleTransaction.getDuePrice());
			saleTransactionLocal.setProductQuantity(saleTransaction.getProductQuantity());
			//saleTransactionLocal.setModified_By(modified_By);
			//saleTransactionLocal.setUpdatedAt();
	 	return saleTransactionRepository.save(saleTransactionLocal);
		})
		.orElseThrow(() -> new ResourceNotFoundException("PostId " + id +" not found"));
	}
	
	
	@DeleteMapping("/delete-saleTransaction/{id}")
	public ResponseEntity<?> deleteSaleTransaction(@PathVariable Integer id) {
        return saleTransactionRepository.findById(id).map(saleTransactionLocal -> {
        	saleTransactionRepository.delete(saleTransactionLocal);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("SaleTransactionId " + id + " not found"));
    }
}
