package com.exercise.dao.mappers;

import com.exercise.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by User_2 on 10/25/2016.
 */
public interface AccountMapper {


	@Insert("INSERT INTO accounts (account_number, balance) " +
			"VALUES (#{accountNumber}, #{balance}) ON DUPLICATE KEY UPDATE balance = #{balance}")
	int insertOrUpdate(Account account);

	@Update("UPDATE accounts SET balance = #{balance} WHERE account_number = #{accountNumber}")
	int update(Account account);

	@Select("SELECT account_number, balance FROM accounts WHERE account_number = #{accountNumber}")
	Account findAccountByAccountNumber(Long accountNumber);

	@Select("SELECT account_number, balance, created_time, updated_time FROM accounts")
	List<Account> findAllAccounts();
}
