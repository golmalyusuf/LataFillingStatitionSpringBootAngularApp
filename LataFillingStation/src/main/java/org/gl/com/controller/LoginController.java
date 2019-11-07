package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.gl.com.entity.Login; 
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.LoginRepository; 
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
public class LoginController {
	@Autowired
	public LoginRepository loginRepository; 
	
	@GetMapping("/logins")
	public List<Login> getAllLogins(){
		return (List<Login>) loginRepository.findAll();
	}
	
	@GetMapping("/login/{id}")
	public Optional<Login> getLogin(@PathVariable Integer id){
		return loginRepository.findById(id);
	}
	
	@PostMapping("/save-Login")
	public ResponseEntity<Void> createLogin(@RequestBody Login login){
		Login loginCreated = loginRepository.save(login);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(loginCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-login/{id}")
	public Login editLogin(@PathVariable Integer id, @Valid @RequestBody Login login){
		return loginRepository.findById(id).map(loginLocal -> {
			loginLocal.setLoginDate(login.getLoginDate());
			loginLocal.setUser(login.getUser());
			loginLocal.setUpdatedBy(login.getUpdatedBy()); //NEED TO SET CURRENT USER
			loginLocal.setUpdatedAt(login.getUpdatedAt());  //NEED TO SET CURRENT TIME
			return loginRepository.save(loginLocal);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
	
	
	
	@DeleteMapping("/delete-login/{id}")
	public ResponseEntity<?> deleteLogin(@PathVariable Integer id) {
        return loginRepository.findById(id).map(login -> {
        	loginRepository.delete(login);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("LoginId " + id + " not found"));
    }
}
