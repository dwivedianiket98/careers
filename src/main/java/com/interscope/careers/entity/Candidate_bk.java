package com.interscope.careers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Candidate_bk {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String email;
	private String password;
	private Integer age;
	private String address;
	private String skills;
	private Integer noticePeriod;
	private String disability;

	private String resume;

	public Candidate_bk(String name, String email, String password, Integer age, String address, String resume,
			String skills, Integer noticePeriod, String disability) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.address = address;
		this.resume = resume;
		this.skills = skills;
		this.noticePeriod = noticePeriod;
		this.disability = disability;
	}

	public Candidate_bk() {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
