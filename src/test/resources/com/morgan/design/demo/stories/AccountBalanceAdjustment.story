Narrative:
In order to move an account in and out of debt
As a Accountant
I want to amend the balance of that account


Scenario: Account Moving to Debt
Given the account balance is 100
    and the account is not in debt
When the accountant reduces the balance by 120
Then the account balance should be -20
	and the account should be in debt
	and the total debt should be in debt 20


Scenario: Account Has Been Cleared
Given the account balance is -20
	and the account is in debt
When the account gets debited 20
Then the account balance should be 0
	and the account should not be in debt
	and the total debt should be in debt 0