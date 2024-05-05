package com.interscope.careers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseModel {
	private String candidateName;
	private String email;
	private Integer candidateAge;
	private String address;
	private String resume;
}
