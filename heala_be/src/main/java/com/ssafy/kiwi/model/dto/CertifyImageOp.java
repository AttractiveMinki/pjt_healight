package com.ssafy.kiwi.model.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertifyImageOp {
	
	private SimpleDateFormat date;
	private List<CertifyImageSimpleOp> list; 
	
}