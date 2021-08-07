package com.ssafy.kiwi.model.dto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertifyImageOp {
	
	private String date;
	private List<CertifyImageSimpleOp> list; 
	
}