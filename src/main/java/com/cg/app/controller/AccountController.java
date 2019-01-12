
package com.cg.app.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.service.SavingsAccountService;

@Controller
@SessionAttributes("accounts")
public class AccountController {

	int flagSortName = 1;
	int flagSortNumber = 1;
	int flagSortBalance = 1;
	int flagSortBySalaried = 1;

	@Autowired
	private SavingsAccountService service;

	@RequestMapping("/index")
	public String home() {
		return "index";
	}

	@RequestMapping("/getAll")
	public String getAllSavingsAccounts(Model model) {
		List<SavingsAccount> accounts = service.getAllSavingsAccount();
		model.addAttribute("accounts", accounts);
		// System.out.println(accounts);
		return "AccountDetails";
	}

	@RequestMapping("/searchForUpdate")
	public String updateForm() {
		return "searchForUpdate";
	}

	@RequestMapping("/updateForm")
	public String getUpdateForm(HttpServletRequest request, Model model) {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		SavingsAccount account = service.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "UpdateForm";
	}

	@RequestMapping("/updateResult")
	public String updateResult(HttpServletRequest request, Model model) {
		String accountHolderName = request.getParameter("accountHolderName");
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		double accBal = Double.parseDouble(request.getParameter("accountBalance"));
		boolean salary = request.getParameter("salary").equalsIgnoreCase("no") ? false : true;
		int account = service.updateAccount(accountNumber, salary, accountHolderName);
		List<SavingsAccount> accounts = service.getAllSavingsAccount();
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}

	@RequestMapping("/addNewSAForm")
	public String createNewAccount() {
		return "addNewSAForm";
	}

	@RequestMapping("/createAccount")
	public String createNewsavingsAccount(@RequestParam("txtAccHN") String accountHolderName,
			@RequestParam("txtBalance") double accountBalance, @RequestParam("rdSalary") boolean salary) {
		service.createNewAccount(accountHolderName, accountBalance, salary);
		return "redirect:getAll";
	}

	@RequestMapping("/close")
	public String closeAccountForm() {
		return "CloseAccountForm";
	}

	@RequestMapping("/closeAccount")
	public String getAllSavingAccounts(@RequestParam("accountNumber") int accountNumber) {
		service.deleteAccount(accountNumber);
		return "redirect:getAll";
	}

	@RequestMapping("/currentBalanceForm")
	public String currentBalanceForm() {
		return "CurrentBalanceForm";
	}

	@RequestMapping("/currentBalance")
	public String currentBalance(@RequestParam("accountNumber") int accountNumber, Model model) {
		double balance;
		balance = service.checkAccountBalance(accountNumber);
		model.addAttribute("balance", balance);
		return "DisplayBalance";
	}

	@RequestMapping("/searchIdForm")
	public String searchByIdForm() {
		return "SearchForm";
	}

	@RequestMapping("/searchId")
	public String searchById(@RequestParam("txtAccountNumber") int accountNumber, Model model) {
		SavingsAccount account = service.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "AccountDetailsForSearch";
	}

	@RequestMapping("/searchNameForm")
	public String searchByNameForm() {
		return "searchByName";
	}

	@RequestMapping("/searchName")
	public String searchByName(@RequestParam("txtAccHN") String accountHolderName, Model model) {
		SavingsAccount account = service.getAccountByName(accountHolderName);
		model.addAttribute("account", account);
		return "DisplayByName";
	}

	@RequestMapping("/searchByBalanceForm")
	public String searchByBalanceForm() {
		return "searchByBalance";
	}

	@RequestMapping("/searchByBalance")
	public String searchByBalance(@RequestParam("txtMinBalance") double minmumBalance,
			@RequestParam("txtMaxBalance") double maximumBalance, Model model) {
		List<SavingsAccount> accounts = service.getAccountByBalance(minmumBalance, maximumBalance);
		model.addAttribute("accounts", accounts);
		return "DisplayByBalance";
	}

	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "withdrawForm";
	}

	@RequestMapping("/withdrawAmount")
	public String withdraw(@RequestParam("accId") int accountNumber, @RequestParam("amount") double amount) {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.withdraw(savingsAccount, amount);
		return "redirect:getAll";

	}

	@RequestMapping("/deposit")
	public String depositForm() {
		return "depositForm";
	}

	@RequestMapping("/depositAmount")
	public String deposit(@RequestParam("accId") int accountNumber, @RequestParam("amount") double amount) {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.deposit(savingsAccount, amount);

		return "redirect:getAll";
	}

	@RequestMapping("/fundTansfer")
	public String fundTransfer() {
		return "fundTransfer";
	}

	@RequestMapping("/transferFunds")
	public String transfer(@RequestParam("sendersAccId") int sender, @RequestParam("receiversAccId") int receiver,
			@RequestParam("amount") double amount) {
		SavingsAccount senderAccount = service.getAccountById(sender);
		SavingsAccount receiverAccount = service.getAccountById(receiver);
		service.fundTransfer(senderAccount, receiverAccount, amount);
		return "redirect:getAll";
	}

	@RequestMapping("/sortByAccountHolderName")
	public String sortByAccountHolderName(Model model) {
		final int flag = flagSortName;
		Collection<SavingsAccount> accounts = service.getAllSavingsAccount();
		Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount o1, SavingsAccount o2) {
				return flag * o1.getBankAccount().getAccountHolderName()
						.compareTo(o2.getBankAccount().getAccountHolderName());
			}
		});
		accountSet.addAll(accounts);
		if (flagSortName == 1) {
			flagSortName = -1;
		} else {
			flagSortName = 1;
		}
		model.addAttribute("accounts", accountSet);
		return "AccountDetails";

	}

	@RequestMapping("/sortByAccountNumber")
	public String sortByAccountNumber(Model model) {
		final int flag1 = flagSortNumber;
		Collection<SavingsAccount> accounts = service.getAllSavingsAccount();
		Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {

			@Override
			public int compare(SavingsAccount o1, SavingsAccount o2) {
				return flag1 * o1.getBankAccount().getAccountNumber() - o2.getBankAccount().getAccountNumber();
			}

		});
		accountSet.addAll(accounts);
		if (flagSortNumber == 1) {
			flagSortNumber = -1;
		} else {
			flagSortNumber = 1;
		}
		model.addAttribute("accounts", accountSet);
		return "AccountDetails";
	}

	@RequestMapping("/sortByAccountBalance")
	public String sortByAccountBalance(Model model) {
		final int flag2 = flagSortBalance;
		Collection<SavingsAccount> accounts = service.getAllSavingsAccount();
		Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {

			@Override
			public int compare(SavingsAccount o1, SavingsAccount o2) {
				return (int) (flag2 * o1.getBankAccount().getAccountBalance()
						- o2.getBankAccount().getAccountBalance());
			}

		});
		accountSet.addAll(accounts);
		if (flagSortBalance == 1) {
			flagSortBalance = -1;
		} else {
			flagSortBalance = 1;
		}
		model.addAttribute("accounts", accountSet);
		return "AccountDetails";
	}

	@RequestMapping("/sortBySalary")
	public String sortBySalaried(Model model) {
		final int flag3 = flagSortBySalaried;
		Collection<SavingsAccount> accounts = service.getAllSavingsAccount();
		Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				if (arg0.isSalary())
					return flag3 * -1;
				else
					return flag3 * +1;
			}
		});
		accountSet.addAll(accounts);
		if (flagSortBySalaried == 1) {
			flagSortBySalaried = -1;
		} else {
			flagSortBySalaried = 1;
		}
		model.addAttribute("accounts", accountSet);
		return "AccountDetails";
	}

}
