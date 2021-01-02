package com.contaazul.bankslipchallenge.entities.enums;

import com.contaazul.bankslipchallenge.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankslipStatus {

	CANCELED("CANCELED"),
	PENDING("PENDING"),
	PAID("PAID");

	private String status;

	public static BankslipStatus from(String name) {
		for (BankslipStatus bankslip : values()) {
			if (bankslip.name().equals( name ))
				return bankslip;
		}
		throw new BusinessException("Status não encontrado");
	}
}
