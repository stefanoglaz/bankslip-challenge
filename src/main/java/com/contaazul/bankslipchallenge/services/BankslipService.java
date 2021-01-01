package com.contaazul.bankslipchallenge.services;

import com.contaazul.bankslipchallenge.entities.Bankslip;
import com.contaazul.bankslipchallenge.repositories.BankslipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankslipService {

	private BankslipRepository repository;

	public void save(Bankslip bankslip){
		repository.save( bankslip );
	}

}
