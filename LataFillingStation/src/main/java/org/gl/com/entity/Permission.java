package org.gl.com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "role_name", nullable = false)
	private String roleName;

	@Column(name = "role_description", nullable = false)
	private String description;
	
	//@OneToMany(mappedBy = "permission")
    //private List<User> users = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	*/

	public Permission() {
		super();
	}

	public Permission(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}
}
