package com.exercise.service;

import com.exercise.domain.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User_2 on 10/24/2016.
 */
public interface BankService {
	int transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount);
	List<Account> findAllAccounts();
	BigDecimal checkBalance(Long accountNumber);
}
