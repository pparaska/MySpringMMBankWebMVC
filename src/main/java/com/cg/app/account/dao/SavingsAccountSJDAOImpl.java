package com.cg.app.account.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.account.SavingsAccount;
//import com.cg.app.account.util.DBUtil;
import com.cg.app.exception.AccountNotFoundException;

@Repository
@Primary
public class SavingsAccountSJDAOImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public SavingsAccount createNewAccount(SavingsAccount account) {
		jdbcTemplate.update("INSERT INTO ACCOUNT (account_hn,account_bal,salaried,account_type) VALUES(?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountHolderName(),
						account.getBankAccount().getAccountBalance(), account.isSalary(), "SA" });

		return null;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber) {
		return jdbcTemplate.queryForObject("SELECT * FROM account where account_id=?", new Object[] { accountNumber },
				new SavingsAccountMapper());

	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) {
		jdbcTemplate.update("DELETE FROM account WHERE account_id=?", new Object[] { accountNumber });
		return null;
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName) {

		return jdbcTemplate.queryForObject("SELECT * FROM account where account_hn=?",
				new Object[] { accountHolderName }, new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> getAccountByBalance(double minmumBalance, double maximumBalance) {
		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? AND ?",
				new Object[] { minmumBalance, maximumBalance }, new SavingsAccountMapper());
	}

	@Override
	public double checkAccountBalance(int accountNumber) {
		return jdbcTemplate.queryForObject("SELECT account_bal FROM account where account_id=?",
				new Object[] { accountNumber }, Double.class);
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() {

		return jdbcTemplate.query("SELECT * from account", new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice) {
		String query = null;
		switch (choice) {
		case 1:
			query = "SELECT * FROM account ORDER BY account_id";
			break;

		case 2:
			query = "SELECT * FROM account ORDER BY account_hn";
			break;

		case 3:
			query = "SELECT * FROM account ORDER BY account_bal";
			break;
		}

		return jdbcTemplate.query(query, new SavingsAccountMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) {
		jdbcTemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",
				new Object[] { currentBalance, accountNumber });

	}

	@Override
	public int updateAccount(int accountNumber, boolean salary, String accountHolderName) {
		return jdbcTemplate.update("UPDATE ACCOUNT SET account_hn=?,salaried=? WHERE account_id=?",
				new Object[] { accountHolderName, salary, accountNumber });
	}

	/*
	 * public boolean updateAccountType(SavingsAccount account){
	 * 
	 * jdbcTemplate.
	 * update("UPDATE ACCOUNT SET account_hn=?,salary=? where account_id=?", new
	 * Object[] { account.getBankAccount().getAccountHolderName(),
	 * account.isSalary(), account.getBankAccount().getAccountNumber() });
	 * 
	 * return false; }
	 */
}
