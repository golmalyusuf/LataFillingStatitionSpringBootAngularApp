package org.gl.com.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sales_transactions")
public class SaleTransaction extends AuditModel{
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "SALE_TRA_SEQUENCE_GENERATOR", sequenceName = "SALE_TRA_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SALE_TRA_SEQUENCE_GENERATOR")
    private Integer id;
    

	@Column(name = "product_name", nullable = false, unique=true)
	private String productName;
	
	@Column(name = "product_retail_price", nullable = false)
	private Double productRetailPrice;
	
	@Column(name = "total_price", nullable = false)
	private Double totalPrice;
	
	@Column(name = "paid_price", nullable = false)
	private Double paidPrice;
	
	@Column(name = "due_price", nullable = false)
	private Double duePrice;
	
	@Column(name = "product_quantity", nullable = false)
	private Double productQuantity;
	
	@Column(name = "product_unit", nullable = false)
	private String productUnit;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sale_date_time", nullable = false)
    private Date saleDtTm;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
 	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;
	
 	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "saleTransaction", orphanRemoval = true)
	private List<Payment> payments = new ArrayList<Payment>();
	 
 	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductRetailPrice() {
		return productRetailPrice;
	}

	public void setProductRetailPrice(Double productRetailPrice) {
		this.productRetailPrice = productRetailPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(Double paidPrice) {
		this.paidPrice = paidPrice;
	}

	public Double getDuePrice() {
		return duePrice;
	}

	public void setDuePrice(Double duePrice) {
		this.duePrice = duePrice;
	}

	public Double getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Double productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Date getSaleDtTm() {
		return saleDtTm;
	}

	public void setSaleDtTm(Date saleDtTm) {
		this.saleDtTm = saleDtTm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public SaleTransaction() {
		super();
	}
	
	public SaleTransaction(String productName, Double productRetailPrice, Double totalPrice, Double paidPrice,
			Double duePrice, Double productQuantity, Date saleDtTm, User user, Customer customer, Product products,
			List<Payment> payments, String productUnit) {
		super();
		this.productName = productName;
		this.productRetailPrice = productRetailPrice;
		this.totalPrice = totalPrice;
		this.paidPrice = paidPrice;
		this.duePrice = duePrice;
		this.productQuantity = productQuantity;
		this.saleDtTm = saleDtTm;
		this.user = user;
		this.customer = customer;
		this.products = products;
		this.payments = payments;
		this.productUnit = productUnit; 
	}
	
}
