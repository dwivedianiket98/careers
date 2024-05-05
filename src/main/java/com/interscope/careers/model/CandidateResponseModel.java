package com.interscope.careers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseModel {
	private String candidateName;
	private Integer candidateAge;
	private String address;
	private String resume;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public Integer getCandidateAge() {
		return candidateAge;
	}

	public void setCandidateAge(Integer candidateAge) {
		this.candidateAge = candidateAge;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public CandidateResponseModel(String candidateName, String email, Integer candidateAge, String address, String resume) {
		super();
		this.candidateName = candidateName;
		this.email = email;
		this.candidateAge = candidateAge;
		this.address = address;
		this.resume = resume;
	}
}
