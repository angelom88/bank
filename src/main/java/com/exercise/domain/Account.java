package com.exercise.domain;

import java.math.BigDecimal;

/**
 * Created by User_2 on 10/25/2016.
 */
public class Account {
	private Long accountNumber;
	private BigDecimal balance;

	public Account() {
		super();
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
