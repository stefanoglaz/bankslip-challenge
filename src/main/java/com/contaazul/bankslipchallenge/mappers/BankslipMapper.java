package com.contaazul.bankslipchallenge.mappers;

import com.contaazul.bankslipchallenge.dtos.BankslipCreateCommand;
import com.contaazul.bankslipchallenge.dtos.BankslipDto;
import com.contaazul.bankslipchallenge.entities.Bankslip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankslipMapper {

	public Bankslip map(BankslipCreateCommand command){
		Bankslip bankslip = Bankslip.builder()
				.dueDate( command.getDueDate() )
				.totalInCents( command.getTotalInCents() )
				.customer( command.getCustomer() )
				.status( command.getStatus() )
				.build();

		bankslip.calculateFine();

		return bankslip;
	}

	public BankslipDto map( Bankslip bankslip) {
		return BankslipDto.builder()
				.id( bankslip.getId() )
				.dueDate( bankslip.getDueDate() )
				.customer( bankslip.getCustomer() )
				.totalInCents( bankslip.getTotalInCents() )
				.build();
	}
}
