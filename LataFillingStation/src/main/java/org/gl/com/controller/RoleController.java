package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.gl.com.entity.Role;
import org.gl.com.entity.exception.ResourceNotFoundException;
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
public class RoleController {
	@Autowired
	public RoleRepository roleRepository; 
	
	@GetMapping("/roles")
	public List<Role> getAllRoles(){
		return (List<Role>) roleRepository.findAll();
	}
	
	@GetMapping("/role/{id}")
	public Optional<Role> getRole(@PathVariable Integer id){
		return roleRepository.findById(id);
	}
	
	@PostMapping("/save-role")
	public ResponseEntity<Void> createRole(@RequestBody Role role){
		Role roleCreated = roleRepository.save(role);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roleCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-role/{id}")
	public Role editRole(@PathVariable Integer id, @Valid @RequestBody Role role){
		return roleRepository.findById(id).map(roleLocal -> {
			roleLocal.setRoleName(role.getRoleName());
			roleLocal.setDescription(role.getDescription());
			roleLocal.setModified_By(role.getModified_By()); //NEED TO SET CURRENT USER
			roleLocal.setUpdatedAt(role.getUpdatedAt());  //NEED TO SET CURRENT TIME
			return roleRepository.save(roleLocal);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
	
	
	
	@DeleteMapping("/delete-role/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
        return roleRepository.findById(id).map(role -> {
            roleRepository.delete(role);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("RoleId " + id + " not found"));
    }
}