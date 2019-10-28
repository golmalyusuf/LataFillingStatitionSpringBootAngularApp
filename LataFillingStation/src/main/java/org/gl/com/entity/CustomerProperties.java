package org.gl.com.entity;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_properties")
public class CustomerProperties extends AuditModel{
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "CUS_PRO_SEQUENCE_GENERATOR", sequenceName = "CUS_PRO_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUS_PRO_SEQUENCE_GENERATOR")
    private Integer id;
    
	@Column(name = "property_type", nullable = false)
	private String propertyType;
	
	@Column(name = "number_plate", nullable = false, unique = true)
	private String numberplate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnore
	private Customer customer;

	public CustomerProperties() {
		super();
	}

	public CustomerProperties(String propertyType, String numberplate, Customer customer) {
		super();
		this.propertyType = propertyType;
		this.numberplate = numberplate;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getNumberplate() {
		return numberplate;
	}

	public void setNumberplate(String numberplate) {
		this.numberplate = numberplate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
