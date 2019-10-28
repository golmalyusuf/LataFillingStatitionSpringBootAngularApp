package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.gl.com.entity.CustomerProperties;
import org.gl.com.entity.Role;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.CustomerPropertyRepository;
import org.gl.com.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/lata")
public class CustomerPropertiesController {
	@Autowired
	public CustomerPropertyRepository customerPropertyRepository; 
	
	@GetMapping("/customer_properties")
	public List<CustomerProperties> getAllRoles(){
		return (List<CustomerProperties>) customerPropertyRepository.findAll();
	}
	
	@GetMapping("/customer_properties/{id}")
	public Optional<CustomerProperties> getCustomerProperty(@PathVariable Integer id){
		return customerPropertyRepository.findById(id);
	}
	
	@PostMapping("/save-customer_property")
	public ResponseEntity<Void> createCustomerProperty(@RequestBody CustomerProperties customerProperties){
		CustomerProperties customerPropertyCreated = customerPropertyRepository.save(customerProperties);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customerPropertyCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-customer_property/{id}")
	public CustomerProperties editCustomerProperty(@PathVariable Integer id, @Valid @RequestBody CustomerProperties customerProperty){
		return customerPropertyRepository.findById(id).map(customerPropertyLocal -> {
			customerPropertyLocal.setPropertyType(customerProperty.getPropertyType());
			customerPropertyLocal.setNumberplate(customerProperty.getNumberplate());
			customerPropertyLocal.setModified_By(customerProperty.getModified_By()); //NEED TO SET CURRENT USER
			customerPropertyLocal.setUpdatedAt(customerProperty.getUpdatedAt());  //NEED TO SET CURRENT TIME
			return customerPropertyRepository.save(customerPropertyLocal);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
	
	@DeleteMapping("/delete-customer_property/{id}")
	public ResponseEntity<?> deleteCustomerProperty(@PathVariable Integer id) {
        return customerPropertyRepository.findById(id).map(customerProperties -> {
        	customerPropertyRepository.delete(customerProperties);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("RoleId " + id + " not found"));
    }
}
