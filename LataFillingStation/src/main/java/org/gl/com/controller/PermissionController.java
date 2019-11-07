package org.gl.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.gl.com.entity.Permission;
import org.gl.com.entity.exception.ResourceNotFoundException;
import org.gl.com.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class PermissionController {

	@Autowired
	public PermissionRepository permissionRepository; 
	
	@GetMapping("/permissions")
	public List<Permission> getAllPermissions(){
		return (List<Permission>) permissionRepository.findAll();
	}
	
	@GetMapping("/permission/{id}")
	public Optional<Permission> getPermission(@PathVariable Long id){
		return permissionRepository.findById(id);
	}
	
	@PostMapping("/save-permission")
	public ResponseEntity<Void> createPermission(@Valid @RequestBody Permission permission){
		Permission permissionCreated = permissionRepository.save(permission);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(permissionCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/edit-permission/{id}")
	public Permission editPermission(@PathVariable Long id, @Valid @RequestBody Permission permission){
		return permissionRepository.findById(id).map(permissionLocal -> {
			permissionLocal.setRoleName(permission.getRoleName());
			permissionLocal.setDescription(permission.getDescription());
			permissionLocal.setUpdatedBy(permission.getUpdatedBy()); //NEED TO SET CURRENT TIME USER
			permissionLocal.setUpdatedAt(permission.getUpdatedAt()); //NEED TO SET CURRENT TIME
			return permissionRepository.save(permissionLocal);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
	
	
	@DeleteMapping("/delete-permission/{id}")
	public ResponseEntity<?> deletePermission(@PathVariable Long id) {
        return permissionRepository.findById(id).map(permission -> {
            permissionRepository.delete(permission);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PermissionId " + id + " not found"));
    }
}
