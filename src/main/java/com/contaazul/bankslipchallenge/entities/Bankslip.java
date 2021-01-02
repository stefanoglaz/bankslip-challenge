package com.contaazul.bankslipchallenge.entities;

import com.contaazul.bankslipchallenge.entities.enums.BankslipStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Bankslip extends BaseEntity{

	private LocalDate dueDate;
	private BigDecimal totalInCents;
	private String customer;
	@Builder.Default
	private BigDecimal fine = BigDecimal.ZERO;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private BankslipStatus status = BankslipStatus.PENDING;

	public void calculateFine(){
		if(LocalDate.now().compareTo( dueDate ) > 0 ) {
			if (DAYS.between( dueDate, LocalDate.now() ) <= 10 )
				fine = totalInCents.multiply( BigDecimal.valueOf( 0.005 ) );
			else
				fine = totalInCents.multiply( BigDecimal.valueOf( 0.01 ) );
		}
	}

}
