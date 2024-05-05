package com.interscope.careers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "UserDetails")
public class User {

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
	private Integer roleId;

	@Lob
	private byte[] resume;

	public User(String name, String email, String password, Integer age, String address, Integer roleId, byte[] resume) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.address = address;
		this.roleId = roleId;
		this.resume = resume;
	}

	public User(String name, String email, String password, Integer age, String address, Integer roleId) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.address = address;
		this.roleId = roleId;
	}

}
