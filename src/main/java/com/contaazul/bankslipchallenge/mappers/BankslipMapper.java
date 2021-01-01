package com.contaazul.bankslipchallenge.mappers;

import com.contaazul.bankslipchallenge.dtos.BankslipDto;
import com.contaazul.bankslipchallenge.entities.Bankslip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankslipMapper {


	public Bankslip map(BankslipDto dto){
		Bankslip bankslip = Bankslip.builder()
				.dueDate( dto.getDueDate() )
				.totalInCents( dto.getTotalInCents() )
				.customer( dto.getCustomer() )
				.status( dto.getStatus() )
				.build();

		bankslip.calculateFine();

		return bankslip;
	}
}
