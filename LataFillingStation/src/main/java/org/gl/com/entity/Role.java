package org.gl.com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role extends AuditModel {
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", sequenceName = "USER_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
    private Integer id;

    @NotNull 
	@Column(name = "role_name", nullable = false)
	private String roleName;

    @NotNull
	@Column(name = "role_description", nullable = false)
	private String description;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
 
	public Role() {
		super();
	}

	public Role(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	} 
}
