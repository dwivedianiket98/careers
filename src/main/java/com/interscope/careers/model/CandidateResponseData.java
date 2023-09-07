package com.interscope.careers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseData {
	private String candidateName;
	private Integer candidateAge;
	private String address;
	private String resume;
	private String skills;
	private Integer noticePeriod;
	private String disability;
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

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Integer getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public CandidateResponseData(String candidateName, String email, Integer candidateAge, String address,

			String skills, Integer noticePeriod, String disability, String resume) {
		super();
		this.candidateName = candidateName;
		this.email = email;
		this.candidateAge = candidateAge;
		this.address = address;
		this.skills = skills;
		this.noticePeriod = noticePeriod;
		this.disability = disability;
		this.resume = resume;
	}


}
