package com.contaazul.bankslipchallenge.controllers;

import com.contaazul.bankslipchallenge.dtos.BankslipCreateCommand;
import com.contaazul.bankslipchallenge.dtos.BankslipDto;
import com.contaazul.bankslipchallenge.entities.Bankslip;
import com.contaazul.bankslipchallenge.entities.enums.BankslipStatus;
import com.contaazul.bankslipchallenge.exceptions.ResponseDetail;
import com.contaazul.bankslipchallenge.mappers.BankslipMapper;
import com.contaazul.bankslipchallenge.services.BankslipService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rest/bankslips", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BankslipController {

	private BankslipService service;
	private BankslipMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDetail create(@RequestBody @Valid BankslipCreateCommand command,
			@RequestHeader("X-TenantId") Long tenantId) {

		Bankslip bankslip = mapper.map( command );

		bankslip.setTenantId( tenantId );
		service.save( bankslip );

		return ResponseDetail.builder()
				.title( "Created" )
				.detail( "Bankslip created" )
				.build();
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<BankslipDto> listAll(@RequestHeader("X-TenantId") Long tenantId) {
		return service.listAll( tenantId );
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Object getBankslip(@RequestHeader("X-TenantId") Long tenantId, @PathVariable String id) {
		try {
			return service.findById( tenantId, UUID.fromString( id ) );
		} catch (Exception e) {
			return ResponseDetail.builder().title( "Bad Request" )
					.detail( "Invalid id provided - it must be a valid UUID" ).build();
		}
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseDetail updateBankslip(@RequestHeader("X-TenantId") Long tenantId, @PathVariable UUID id,
			@RequestBody Map<String, String> payload) {

		service.update( tenantId, id, payload );

		if (BankslipStatus.PAID.toString().equals( payload.get( "status" ) ))
			return ResponseDetail.builder().title( "Ok" ).detail( "Bankslip paid" ).build();
		else
			return ResponseDetail.builder().title( "Ok" ).detail( "Bankslip canceled" ).build();

	}
}
