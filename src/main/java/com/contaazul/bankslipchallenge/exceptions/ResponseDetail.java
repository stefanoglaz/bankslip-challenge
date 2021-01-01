package com.contaazul.bankslipchallenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetail {

	private String title;
	private String detail;

}
