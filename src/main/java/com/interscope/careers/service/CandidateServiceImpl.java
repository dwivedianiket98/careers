package com.interscope.careers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.interscope.careers.entity.Candidate;
import com.interscope.careers.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

//	insert into candidate values(10001, 'jhandha tola', 23, 'No', 'gandhi@gmail.com', 'Gandhi Boi', 30, 'admin@123', 'not available', 'sleeping');

	@Autowired
	private CandidateRepository candidateRepo;

	@Override
	public List<Candidate> getCandidates() {
		return candidateRepo.findAll();
	}

	@Override
	public Candidate save(String name, String email, String password, Integer age, String address, String skills,
			Integer notice, String disability, MultipartFile resume) {
		Candidate candidate = null;
		try {
			candidate = new Candidate(name, email, password, age, address, skills, notice, disability,
					resume.getBytes());
		} catch (IOException e) {
			System.out.println("[*] error while saving candidate");
		}
		return candidateRepo.save(candidate);
	}

	@Override
	public Candidate getCandidateById(Integer id) {

		Optional<Candidate> candidateOptional = candidateRepo.findById(id);

		if (!candidateOptional.isPresent()) {
			new Exception("Id not found.");
		}

		return candidateOptional.get();
	}

}
