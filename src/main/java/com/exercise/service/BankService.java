package com.exercise.service;

import java.math.BigDecimal;

/**
 * Created by User_2 on 10/24/2016.
 */
public interface BankService {
	int transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount);
}
