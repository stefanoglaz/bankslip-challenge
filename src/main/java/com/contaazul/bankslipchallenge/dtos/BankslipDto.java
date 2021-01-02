package com.contaazul.bankslipchallenge.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
public class BankslipDto {

	private UUID id;
	private LocalDate dueDate;
	private BigDecimal totalInCents;
	private String customer;
}
