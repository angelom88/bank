package com.exercise.service.impl;

import com.exercise.bo.BusinessException;
import com.exercise.dao.mappers.AccountMapper;
import com.exercise.domain.Account;
import com.exercise.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User_2 on 10/24/2016.
 */
@Service("bankService")
public class BankServiceImpl implements BankService {

	@Autowired
	AccountMapper accountMapper;

	private boolean hasSufficientBalance(Account account, BigDecimal amountDeduct) {
		if (account == null) {
			throw new BusinessException("No account number found.");
		} else {
			if (account.getBalance().compareTo(amountDeduct) >= 0) {
				return true;
			}
		}

		return false;
	}

	@Transactional
	private int debit(Account account, BigDecimal amount) {
		account.setBalance(account.getBalance().subtract(amount));
		return accountMapper.update(account);
	}

	@Transactional
	private int credit(Account account, BigDecimal amount) {
		if (account != null) {
			account.setBalance(null == account.getBalance() ? amount : account.getBalance().add(amount));
		}

		return accountMapper.insertOrUpdate(account);
	}

	@Override
	public List<Account> findAllAccounts() {
		return accountMapper.findAllAccounts();
	}

	@Override
	public BigDecimal checkBalance(Long accountNumber) {
		return accountMapper.findAccountByAccountNumber(accountNumber).getBalance();
	}

	@Override
	public synchronized int transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount) {
		int success = 0;
		try {
			Account firstLock, secondLock;

			Account fromAccount = accountMapper.findAccountByAccountNumber(fromAccountNumber);
			Account toAccount = accountMapper.findAccountByAccountNumber(toAccountNumber);
			if (fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) == 0) {
				throw new BusinessException("Cannot transfer to the same account.");
			} else if (fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) < 0) {
				firstLock = fromAccount;
				secondLock = toAccount;
			} else {
				firstLock = toAccount;
				secondLock = fromAccount;
			}

			synchronized (firstLock) {
				synchronized (secondLock) {
					if (hasSufficientBalance(fromAccount, amount)) {
						success = (debit(fromAccount, amount) > 0 && credit(toAccount, amount) > 0) ? 1 : 0;
					} else {
						throw new BusinessException("Insufficient balance");
					}
				}
			}
		} catch (Exception e) {
			new BusinessException("Exception encountered");
		}

		return success;
	}

}
