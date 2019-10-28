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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends AuditModel{
	
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "CUSTOMER_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_SEQUENCE_GENERATOR")
    private Integer id;
    
	@Column(name = "customer_name", nullable = false, unique = true)
	private String customerName;

	@Column(name = "email_id", nullable = false)
	private String emailId;

	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "mobile_number", nullable = false,  unique = true)
	private String mobileNumber;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CustomerProperties> customerProperties;

	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "customer", orphanRemoval = true)
	private List<SaleTransaction> saleTransaction = new ArrayList<SaleTransaction>();
	
	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "customer", orphanRemoval = true)
	private List<Payment> payments = new ArrayList<Payment>();
	
	public Customer() {
		super();
	}

	public Customer(String customerName, String emailId, String address, String mobileNumber,
			Set<CustomerProperties> customerProperties) {
		super();
		this.customerName = customerName;
		this.emailId = emailId;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.customerProperties = customerProperties;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Set<CustomerProperties> getCustomerProperties() {
		return customerProperties;
	}

	public void setCustomerProperties(Set<CustomerProperties> customerProperties) {
		this.customerProperties = customerProperties;
	}

	public List<SaleTransaction> getSaleTransaction() {
		return saleTransaction;
	}

	public void setSaleTransaction(List<SaleTransaction> saleTransaction) {
		this.saleTransaction = saleTransaction;
	}
	
}
