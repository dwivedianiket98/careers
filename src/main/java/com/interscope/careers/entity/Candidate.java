package com.interscope.careers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Candidate {

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank(message = "Name is required!")
	private String name;
	@NotBlank(message = "Email is required!")
	private String email;
	@NotBlank(message = "Password is required!")
	private String password;
	private Integer age;
	private String address;
	private String skills;
	private Integer noticePeriod;
	private String disability;

	@Lob
	@NotEmpty
	private byte[] resume;

	public Candidate() {
		super();
	}

	public Candidate(String name, String email, String password, Integer age, String address, String skills,
			Integer noticePeriod, String disability, byte[] resume) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.address = address;
		this.skills = skills;
		this.noticePeriod = noticePeriod;
		this.disability = disability;
		this.resume = resume;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
