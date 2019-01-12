package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary);

	SavingsAccount getAccountById(int accountNumber);

	SavingsAccount getAccountByName(String accountHolderName);

	List<SavingsAccount> getAccountByBalance(double minmumBalance, double maximumBalance);

	SavingsAccount deleteAccount(int accountNumber)
	/* throws AccountNotFoundException */;

	double checkAccountBalance(int accountNumber);

	List<SavingsAccount> getAllSavingsAccount();

	@Transactional(rollbackFor = Throwable.class)
	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount);

	void deposit(SavingsAccount account, double amount);

	void withdraw(SavingsAccount account, double amount);

	List<SavingsAccount> sortAllSavingsAccount(int choice);

	int updateAccount(int accountNumber, boolean salary, String accountHolderName);
	// boolean updateAccount(SavingsAccount account);
}
