package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.gl.com.entity.Role;
import org.gl.com.entity.User;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.UserRepository;
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
public class UserController {
	@Autowired
	public UserRepository userRepository; 
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUser(@PathVariable Integer id){
		return userRepository.findById(id);
	}
	
	@PostMapping("/save-user")
	@Transactional
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user){
		Role role = user.getRole();
		User userCreated = userRepository.save(user);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-user/{id}")
	public User editUser(@PathVariable Integer id, @Valid @RequestBody User user){
		return userRepository.findById(id).map(userLocal -> {
			userLocal.setFirstName(user.getFirstName());
			userLocal.setLastName(user.getLastName());
			userLocal.setEmailId(user.getEmailId());
			userLocal.setAddress(user.getAddress());
			userLocal.setMobileNumber(user.getMobileNumber());
			userLocal.setRole(user.getRole());
			userLocal.setModified_By(user.getModified_By()); //NEED TO SET CURRENT TIME USER
			userLocal.setUpdatedAt(user.getUpdatedAt()); //NEED TO SET CURRENT TIME
			return userRepository.save(userLocal);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
	
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + " not found"));
    }
}
