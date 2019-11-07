package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.gl.com.entity.Customer;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.data.domain.Sort;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/lata")
public class CustomerController {
	@Autowired
	public CustomerRepository customerRepository; 
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return (List<Customer>) customerRepository.findAll();
	}
	
    @RequestMapping(value = "/customers/get", params = { "page", "size"}, method = RequestMethod.GET)
	public Page<Customer> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<Customer> resultPage = customerRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "customerName")));
		if (page > resultPage.getTotalPages()) {
			System.out.println("Resource Not Found");
		}
		return resultPage;
	}
	
	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomer(@PathVariable Integer id){
		return customerRepository.findById(id);
	} 
	
	@PostMapping("/save-customer")
	@Transactional
	public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer){
		 
		Customer customerCreated = customerRepository.save(customer);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customerCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-customer/{id}")
	public Customer editCustomer(@PathVariable Integer id, @Valid @RequestBody Customer customer){
		return customerRepository.findById(id).map(customerLocal -> {
			customerLocal.setCustomerName(customer.getCustomerName());
			customerLocal.setEmailId(customer.getEmailId());
			customerLocal.setAddress(customer.getAddress());
			customerLocal.setMobileNumber(customer.getMobileNumber());
			customerLocal.setUpdatedBy(customer.getUpdatedBy()); //NEED TO SET CURRENT TIME USER
			customerLocal.setUpdatedAt(customer.getUpdatedAt()); //NEED TO SET CURRENT TIME
			return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
	
	
	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        return customerRepository.findById(id).map(customer -> {
        	customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + id + " not found"));
    }
}
