package org.gl.com.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "productDetails")
public class Product extends AuditModel{
	private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "PRODUCT_SEQUENCE_GENERATOR", sequenceName = "PRODUCT_ID_SEQUENCE")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_SEQUENCE_GENERATOR")
    private Integer id;
    
	@Column(name = "product_name", nullable = false,unique = true)
	private String productName;

	@Column(name = "product_description", nullable = false)
	private String productDescription;
	
	@Column(name = "product_wholesale_price", nullable = false)
	private Double productWholesalePrice;
	
	@Column(name = "product_in_stock", nullable = false)
	private Double productInStock;

	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "products", orphanRemoval = true)
	private List<SaleTransaction> saleTransaction = new ArrayList<SaleTransaction>();
	 
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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductWholesalePrice() {
		return productWholesalePrice;
	}

	public void setProductWholesalePrice(Double productWholesalePrice) {
		this.productWholesalePrice = productWholesalePrice;
	}

	public Double getProductInStock() {
		return productInStock;
	}

	public void setProductInStock(Double productInStock) {
		this.productInStock = productInStock;
	}

	public List<SaleTransaction> getSaleTransaction() {
		return saleTransaction;
	}

	public void setSaleTransaction(List<SaleTransaction> saleTransaction) {
		this.saleTransaction = saleTransaction;
	}

	public Product() {
		super();
	}

	public Product(String productName, String productDescription, Double productWholesalePrice, Double productInStock,
			List<SaleTransaction> saleTransaction) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productWholesalePrice = productWholesalePrice;
		this.productInStock = productInStock;
		this.saleTransaction = saleTransaction;
	}
	
}
