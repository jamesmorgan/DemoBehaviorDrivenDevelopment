package com.morgan.design.demo.domain;

import java.math.BigDecimal;

public class Account {

	private BigDecimal openingBalance;
	private BigDecimal totalDebt;
	private boolean inDebt;

	public void reduceBalance(BigDecimal amount) {
		this.openingBalance = this.openingBalance.subtract(amount);
		if (-1 == this.openingBalance.compareTo(BigDecimal.ZERO)) {
			this.totalDebt = this.openingBalance;
			this.totalDebt = this.totalDebt.abs();
			this.inDebt = true;
		}
	}

	public void increaseBalance(BigDecimal amount) {
		this.openingBalance = this.openingBalance.add(amount);
		if (0 == this.openingBalance.compareTo(BigDecimal.ZERO) || 1 == this.openingBalance.compareTo(BigDecimal.ZERO)) {
			this.totalDebt = BigDecimal.ZERO;
			this.inDebt = false;
		}
	}

	public BigDecimal getOpeningBalance() {
		return this.openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public boolean isInDebt() {
		return inDebt;
	}

	public void setInDebt(boolean inDebt) {
		this.inDebt = inDebt;
	}

	public BigDecimal getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
	}

}
