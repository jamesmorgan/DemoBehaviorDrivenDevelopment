package com.morgan.design.demo.stories;

import java.math.BigDecimal;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.morgan.design.demo.domain.Account;

@Component
public class AccountBalanceAdjustmentSteps {

	private Account account;

	/**
	 * Callback method triggered before each scenario.
	 */
	@BeforeScenario
	public void setUp() {
		account = null;
	}

	/************************************
	 * Scenario: Account Moving to Debt *
	 ************************************/

	@Given("the account balance is $balance and the account is not in debt")
	public void givenAccountBalanceAndNotInDebt(@Named("balance") BigDecimal balance) {
		account = new Account();
		account.setOpeningBalance(balance);
		account.setInDebt(false);
		account.setTotalDebt(BigDecimal.ZERO);
	}

	@When("the accountant reduces the balance by $amount")
	public void whenAccountantReducesBalance(@Named("amount") BigDecimal amount) {
		account.reduceBalance(amount);
	}

	@Then("the account balance should be $balance and the account should be in debt and the total debt should be in debt $debt")
	public void thenAccountBalancesAlteredNegative(@Named("balance") BigDecimal balance, @Named("debt") BigDecimal debt) {
		Assert.assertEquals(account.getOpeningBalance(), balance);
		Assert.assertEquals(account.getTotalDebt(), debt);
		Assert.assertEquals(account.isInDebt(), true);
	}

	/**************************************
	 * Scenario: Account Has Been Cleared *
	 **************************************/

	@Given("the account balance is $balance and the account is in debt and the account total debt is $debt")
	public void givenAccountBalanceAndInDebt(@Named("balance") BigDecimal balance, @Named("debt") BigDecimal debt) {
		account = new Account();
		account.setOpeningBalance(balance);
		account.setInDebt(true);
		account.setTotalDebt(debt);
	}

	@When("the account gets debited by $amount")
	public void whenAccountantDebtsAccount(@Named("amount") BigDecimal amount) {
		account.increaseBalance(amount);
	}

	@Then("the account balance should be $balance and the account should not be in debt and the total debt should be in debt $debt")
	public void thenAccountBalancesAlteredPositive(@Named("balance") BigDecimal balance, @Named("debt") BigDecimal debt) {
		Assert.assertEquals(account.getOpeningBalance(), balance);
		Assert.assertEquals(account.getTotalDebt(), debt);
		Assert.assertEquals(account.isInDebt(), false);
	}
}
