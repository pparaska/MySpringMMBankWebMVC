package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.dao.SavingsAccountDAO;
import com.cg.app.account.factory.AccountFactory;
//import com.cg.app.account.util.DBUtil;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;

	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	@Autowired
	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		/* this.savingsAccountDAO = savingsAccountDAO; */
	}

	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount() {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	public void deposit(SavingsAccount account, double amount) {
		{
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		}
	}

	public void withdraw(SavingsAccount account, double amount) {
		double currentBalance = account.getBankAccount().getAccountBalance();

		currentBalance -= amount;
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}

	@Transactional(rollbackFor = Throwable.class)
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) {
		withdraw(sender, amount);
		deposit(receiver, amount);

	}

	public SavingsAccount getAccountById(int accountNumber) {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	public SavingsAccount getAccountByName(String accountHolderName) {

		return savingsAccountDAO.getAccountByName(accountHolderName);
	}

	public SavingsAccount deleteAccount(int accountNumber)
	/* throws AccountNotFoundException */ {

		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	public double checkAccountBalance(int accountNumber) {
		return savingsAccountDAO.checkAccountBalance(accountNumber);
	}

	public List<SavingsAccount> getAccountByBalance(double minmumBalance, double maximumBalance) {

		return savingsAccountDAO.getAccountByBalance(minmumBalance, maximumBalance);
	}

	public int updateAccount(int accountNumber, boolean salary, String accountHolderName) {
		return savingsAccountDAO.updateAccount(accountNumber, salary, accountHolderName);
	}

	/*
	 * public boolean updateAccount(SavingsAccount account) { return
	 * savingsAccountDAO.updateAccountType(account); }
	 */
	public List<SavingsAccount> sortAllSavingsAccount(int choice) {
		return savingsAccountDAO.sortAllSavingsAccount(choice);
	}

}
