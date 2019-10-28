package org.gl.com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "logins")
public class Login extends AuditModel {
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "LOGIN_SEQUENCE_GENERATOR", sequenceName = "LOGIN_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LOGIN_SEQUENCE_GENERATOR")
    private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "login_at", nullable = false)
	private Date loginDate;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User loginByUsers;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public User getUser() {
		return loginByUsers;
	}

	public void setUser(User user) {
		this.loginByUsers = user;
	}

	public Login() {
		super();
	}

	public Login(Date loginDate, User user) {
		super();
		this.loginDate = loginDate;
		this.loginByUsers = user;
	}
	

}
