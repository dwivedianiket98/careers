package com.interscope.careers.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.interscope.careers.entity.Candidate;

public interface CandidateService {

	List<Candidate> getCandidates();

	Candidate save(String name, String email, String password, Integer age, String address, String skills,
			Integer notice, String disability, MultipartFile file);

	Candidate getCandidateById(Integer id);

//	Candidate save(Candidate candidate);

}
