package com.exercise.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by User_2 on 10/24/2016.
 */
public class TransferRequest {
	@NotNull
	Long fromAccountNumber;
	@NotNull
	Long toAccountNumber;
	@NotNull
	BigDecimal transferAmount;

	public Long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(Long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public Long getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(Long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}
}
