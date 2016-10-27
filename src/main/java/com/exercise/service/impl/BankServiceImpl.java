package com.exercise.service.impl;

import com.exercise.BankApplication;
import com.exercise.bo.BusinessException;
import com.exercise.dao.mappers.AccountMapper;
import com.exercise.domain.Account;
import com.exercise.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by User_2 on 10/24/2016.
 */
@Service("bankService")
public class BankServiceImpl implements BankService {

	@Autowired
	AccountMapper accountMapper;
	private Lock bankLock = new ReentrantLock();
	private Condition hasSufficientFunds = bankLock.newCondition();

	@Transactional
	private int debit(Account account, BigDecimal amount) {
		bankLock.lock();
		try {
			account.setBalance(account.getBalance().subtract(amount));
			return accountMapper.update(account);
		} catch (RuntimeException e) {
			return 0;
		} finally {
			bankLock.unlock();
		}
	}

	@Transactional
	private int credit(Account account, BigDecimal amount) {
		bankLock.lock();
		try {
			if (account != null) {
				account.setBalance(null == account.getBalance() ? amount : account.getBalance().add(amount));
				return accountMapper.update(account);
			}
			return 0;
		} catch (RuntimeException e) {
			return 0;
		} finally {
			bankLock.unlock();
		}
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
	public int transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount) {
		Account fromAccount, toAccount;
		int success = 0;
		BigDecimal transferredAmount = new BigDecimal(0);

		bankLock.lock();
		try {
			fromAccount = accountMapper.findAccountByAccountNumber(fromAccountNumber);
			toAccount = accountMapper.findAccountByAccountNumber(toAccountNumber);
			while (fromAccount.getBalance().compareTo(amount) < 0) {
				hasSufficientFunds.await();
			}
			success = (debit(fromAccount, amount) == 1 && credit(toAccount, amount) == 1) ? 1 : 0;
			hasSufficientFunds.signal();
		} catch (InterruptedException e) {
			//e.printStackTrace(); do nothing instead
		} finally {
			bankLock.unlock();
		}

		return success;
	}


}
