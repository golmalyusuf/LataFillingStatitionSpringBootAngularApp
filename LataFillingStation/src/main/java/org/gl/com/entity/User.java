package org.gl.com.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends AuditModel {

	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", sequenceName = "USER_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
    private Integer id;

    @NotNull 
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email_id", nullable = false)
	private String emailId;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;
 
	@ManyToOne
    @JoinColumn(name = "role_id")
    private Role userRole;

//	@ManyToOne
//    @JoinColumn(name = "permission_id")
//    private Permission userPermission;

	@OneToMany(cascade = CascadeType.ALL,  
	        mappedBy = "user", orphanRemoval = true)
	private List<SaleTransaction> saleTransaction = new ArrayList<SaleTransaction>();
	
	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "user", orphanRemoval = true)
	private List<Expense> expenseByUser = new ArrayList<Expense>();
	
	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "loginByUsers", orphanRemoval = true)
	private List<Login> loginByUser = new ArrayList<Login>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Role getRole() {
		return userRole;
	}

	public void setRole(Role role) {
		this.userRole = role;
	}
 
	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public List<SaleTransaction> getSaleTransaction() {
		return saleTransaction;
	}

	public void setSaleTransaction(List<SaleTransaction> saleTransaction) {
		this.saleTransaction = saleTransaction;
	}

	public List<Expense> getExpenseByUser() {
		return expenseByUser;
	}

	public void setExpenseByUser(List<Expense> expenseByUser) {
		this.expenseByUser = expenseByUser;
	}

	public List<Login> getLoginByUser() {
		return loginByUser;
	}

	public void setLoginByUser(List<Login> loginByUser) {
		this.loginByUser = loginByUser;
	} 

	public User() {
		super();
	}

	public User(@NotNull String firstName, String lastName, String emailId, String address, String mobileNumber,
			Role userRole, List<SaleTransaction> saleTransaction,
			List<Expense> expenseByUser, List<Login> loginByUser) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.userRole = userRole;
		 
		this.saleTransaction = saleTransaction;
		this.expenseByUser = expenseByUser;
		this.loginByUser = loginByUser;
	}
	
	
}
