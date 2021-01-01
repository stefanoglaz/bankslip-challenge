package com.contaazul.bankslipchallenge.dtos;

import com.contaazul.bankslipchallenge.entities.enums.BankslipStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class BankslipDto {

	@NotNull(message = "Uma data de vencimento deve ser informada")
	private LocalDate dueDate;

	@Positive(message = "O valor do boleto deve ser maior que zero")
	@NotNull(message = "Um valor em cenvatos deve ser informado")
	private BigDecimal totalInCents;

	@NotNull(message = "O boleto deve pertencer a um cliente")
	private String customer;

	@NotNull(message = "Um status para o boleto deve ser informado")
	private BankslipStatus status;
}
