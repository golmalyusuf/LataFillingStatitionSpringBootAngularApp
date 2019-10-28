package org.gl.com.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "expense_details")
public class Expense extends AuditModel{
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "EXPENSE_SEQUENCE_GENERATOR", sequenceName = "EXPENSE_ID_SEQUENCE")

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EXPENSE_SEQUENCE_GENERATOR")
	private Integer id;

	@Column(name = "EXPENSE_DESCRIPION", nullable = false)
	private String expenseDescription;

	@Column(name = "EXPENSE_AMOUNT", nullable = false)
	private BigDecimal expenseAmount;

	@Column(name = "EXPENSE_DATE", nullable = false)
	@CreatedDate
	private Date expenseDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(BigDecimal expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Expense() {
		super();
	}

	public Expense(String expenseDescription, BigDecimal expenseAmount, Date expenseDate, User user) {
		super();
		this.expenseDescription = expenseDescription;
		this.expenseAmount = expenseAmount;
		this.expenseDate = expenseDate;
		this.user = user;
	}
	
}
