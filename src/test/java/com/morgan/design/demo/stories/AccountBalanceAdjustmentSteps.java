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

	@Given("Given the account balance is $balance and the account is not in debt")
	public void givenValidCardAndAccount(@Named("balance") BigDecimal balance) {
		account = new Account();
		account.setOpeningBalance(balance);
		account.setInDebt(true);
		account.setTotalDebt(BigDecimal.ZERO);
	}

	@When("When the accountant reduces the balance by $amount")
	public void whenAccountHolderRequestsMoney(@Named("amount") BigDecimal amount) {
		account.reduceBalance(amount);
	}

	@Then("Then the account balance should be $balance and the account should be in debt and the total debt should be in debt $debt")
	public void thenInsufficientFundsShouldBeReported(@Named("balance") BigDecimal balance, @Named("debt") BigDecimal debt) {
		Assert.assertEquals(account.getOpeningBalance(), balance);
		Assert.assertEquals(account.getTotalDebt(), debt);
		Assert.assertEquals(account.isInDebt(), true);
	}
}
