package com.exercise.controller;

import com.exercise.bo.BankResponse;
import com.exercise.bo.BusinessException;
import com.exercise.request.TransferRequest;
import com.exercise.service.impl.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Created by User_2 on 10/24/2016.
 */
@RestController
public class BankController {
	@Autowired
	private BankServiceImpl bankService;

	@RequestMapping("/test")
	public BankResponse test() {
		return BankResponse.success(bankService.findAllAccounts(), "fetch successfully");
	}

	@RequestMapping(value = "/checkBalance", consumes = "application/json")
	public BankResponse checkBalance(@RequestParam("accountNumber") Long accountNumber) {
		return BankResponse.success(bankService.checkBalance(accountNumber), "fetch successfully");
	}
	// start money transactions

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public BankResponse transfer(@RequestBody @Valid TransferRequest request) {
		try {
			System.out.println("transfer: from: " + request.getFromAccountNumber() + " to:" + request.getToAccountNumber() + " amount: " + request.getTransferAmount());
			return BankResponse.success(bankService.transfer(request.getFromAccountNumber(), request.getToAccountNumber(), request.getTransferAmount()), "Transfer successfully");
		} catch (BusinessException e) {
			return BankResponse.failed(e.getMessage());
		}
	}
}
