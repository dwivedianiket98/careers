package com.interscope.careers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterResponseModel implements UserResponseModel {
	private String userName;
	private String email;
	private Integer userAge;
	private String address;
	private Integer userId;

}
