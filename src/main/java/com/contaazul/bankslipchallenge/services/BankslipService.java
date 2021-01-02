package com.contaazul.bankslipchallenge.services;

import com.contaazul.bankslipchallenge.dtos.BankslipCreateCommand;
import com.contaazul.bankslipchallenge.dtos.BankslipDto;
import com.contaazul.bankslipchallenge.entities.Bankslip;
import com.contaazul.bankslipchallenge.entities.enums.BankslipStatus;
import com.contaazul.bankslipchallenge.exceptions.BankslipNotFoundException;
import com.contaazul.bankslipchallenge.mappers.BankslipMapper;
import com.contaazul.bankslipchallenge.repositories.BankslipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankslipService {

	private BankslipRepository repository;
	private BankslipMapper mapper;

	public void save(Bankslip bankslip) {
		repository.save( bankslip );
	}

	public List<BankslipDto> listAll(Long tenantId) {
		return repository.findByTenantId( tenantId ).stream().map( bankslip -> mapper.map( bankslip ) )
				.collect( Collectors
						.toList() );
	}

	public Bankslip findById(Long tenantId, UUID id) {
		Bankslip bankslip = repository.findByTenantIdAndId( tenantId, id );
		if (bankslip != null)
			return bankslip;
		throw new BankslipNotFoundException();
	}

	public void update(Long tenantId, UUID id, Map<String, String> parameter) {
		Bankslip bankslip = findById( tenantId, id );
		bankslip.setStatus( BankslipStatus.from( parameter.get( "status" ) ) );
		save( bankslip );
	}
}
