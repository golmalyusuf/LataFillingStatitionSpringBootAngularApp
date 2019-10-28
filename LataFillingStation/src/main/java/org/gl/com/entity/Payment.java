package org.gl.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "PAYMENT_SEQUENCE_GENERATOR", sequenceName = "PAYMENT_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_SEQUENCE_GENERATOR")
    private Integer id;

    @Column(name = "total_amount", nullable = false)
	private Double totalAmount; 
    
    @Column(name = "paid_amount", nullable = false)
	private Double paidAmount; 
    
    @Column(name = "due_amount", nullable = false)
	private Double dueAmount; 
    
    @ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
    
    @ManyToOne
	@JoinColumn(name = "saleTransaction_id", nullable = false)
	private SaleTransaction saleTransaction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SaleTransaction getSaleTransaction() {
		return saleTransaction;
	}

	public void setSaleTransaction(SaleTransaction saleTransaction) {
		this.saleTransaction = saleTransaction;
	}

	public Payment() {
		super();
	}

	public Payment(Double totalAmount, Double paidAmount, Double dueAmount, Customer customer,
			SaleTransaction saleTransaction) {
		super();
		this.totalAmount = totalAmount;
		this.paidAmount = paidAmount;
		this.dueAmount = dueAmount;
		this.customer = customer;
		this.saleTransaction = saleTransaction;
	}
    
}
