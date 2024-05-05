package com.interscope.careers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseModel implements UserResponseModel{
	private String userName;
	private String email;
	private Integer userAge;
	private String address;
	private String resume;
	private Integer userId;
}
