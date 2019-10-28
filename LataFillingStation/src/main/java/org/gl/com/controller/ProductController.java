package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.gl.com.entity.Customer;
import org.gl.com.entity.Product;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.CustomerRepository;
import org.gl.com.repository.ProductRepository;
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
public class ProductController {
	@Autowired
	public ProductRepository productRepository; 
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return (List<Product>) productRepository.findAll();
	}
	
    @RequestMapping(value = "/products/get", params = { "page", "size"}, method = RequestMethod.GET)
	public Page<Product> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<Product> resultPage = productRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "productName")));
		if (page > resultPage.getTotalPages()) {
			System.out.println("Resource Not Found");
		}
		return resultPage;
	}
	
	@GetMapping("/product/{id}")
	public Optional<Product> getProduct(@PathVariable Integer id){
		return productRepository.findById(id);
	} 
	
	@PostMapping("/save-product")
	@Transactional
	public ResponseEntity<Void> createProduct(@Valid @RequestBody Product product){
		Product productCreated = productRepository.save(product);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-product/{id}")
	@Transactional
	public Product editProduct(@PathVariable Integer id, @Valid @RequestBody Product product){
		return productRepository.findById(id).map(productLocal->{
			productLocal.setProductName(product.getProductName());
			productLocal.setProductDescription(product.getProductDescription());
			productLocal.setProductInStock(product.getProductInStock());
			productLocal.setProductWholesalePrice(product.getProductWholesalePrice());
			return productRepository.save(productLocal);
		})
		.orElseThrow(() -> new ResourceNotFoundException("PostId " + id +" not found"));
	}
	
	
	@DeleteMapping("/delete-product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        return productRepository.findById(id).map(product -> {
        	productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
    }
}
