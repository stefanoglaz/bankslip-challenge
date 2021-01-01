package com.contaazul.bankslipchallenge.controllers;

import com.contaazul.bankslipchallenge.dtos.BankslipDto;
import com.contaazul.bankslipchallenge.entities.Bankslip;
import com.contaazul.bankslipchallenge.exceptions.ResponseDetail;
import com.contaazul.bankslipchallenge.mappers.BankslipMapper;
import com.contaazul.bankslipchallenge.services.BankslipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rest/bankslips", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BankslipController {

	private BankslipService service;
	private BankslipMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDetail create(@RequestBody @Valid BankslipDto dto,
			@RequestHeader("X-TenantId") Long tenantId) {

		Bankslip bankslip = mapper.map( dto );

		bankslip.setTenantId( tenantId );
		service.save( bankslip );

		return ResponseDetail.builder()
				.title( "Created" )
				.detail( "Bankslip created" )
				.build();
	}

}
