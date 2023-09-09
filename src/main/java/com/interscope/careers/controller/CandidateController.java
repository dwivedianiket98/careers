package com.interscope.careers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.interscope.careers.entity.Candidate;
import com.interscope.careers.model.CandidateResponseData;
import com.interscope.careers.service.CandidateService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@GetMapping
	public ResponseEntity<List<CandidateResponseData>> getCandidates() {
		List<Candidate> candidates = candidateService.getCandidates();

		List<CandidateResponseData> response = new ArrayList<>();
		candidates.forEach(candidate -> {
			String resumeUrl = "";
			resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/candidates/resume/")
					.path(candidate.getId().toString()).toUriString();
			response.add(new CandidateResponseData(candidate.getName(), candidate.getEmail(), candidate.getAge(),
					candidate.getAddress(), candidate.getSkills(), candidate.getNoticePeriod(),
					candidate.getDisability(), resumeUrl));
		});

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CandidateResponseData> getCandidateById(@PathVariable Integer id) {

		Candidate candidate = candidateService.getCandidateById(id);

		String resumeUrl = "";
		resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/candidates/resume/")
				.path(candidate.getId().toString()).toUriString();

		CandidateResponseData response = new CandidateResponseData(candidate.getName(), candidate.getEmail(),
				candidate.getAge(), candidate.getAddress(), candidate.getSkills(), candidate.getNoticePeriod(),
				candidate.getDisability(), resumeUrl);

		return ResponseEntity.ok(response);
	}

//	@PostMapping(path = "candidates")
//	public ResponseEntity<CandidateResponseData> addCandidate(@RequestBody Candidate candidate) {
//
//		Candidate savedCandidate = candidateService.save(candidate);
//		CandidateResponseData response = new CandidateResponseData(candidate.getName(), candidate.getEmail(),
//				candidate.getAge(), candidate.getAddress(), candidate.getSkills(), candidate.getNoticePeriod(),
//				candidate.getDisability());
//
//		return ResponseEntity.ok(response);
//	}
	@PostMapping
	public ResponseEntity<CandidateResponseData> addCandidate(@RequestParam("resume") MultipartFile resume,
															  @RequestParam @Valid String name,
															  @RequestParam @Valid String email,
															  @RequestParam @Valid String password,
															  @RequestParam Integer age,
															  @RequestParam String skills,
															  @RequestParam String disability,
															  @RequestParam("noticePeriod") Integer notice,
															  @RequestParam String address) {

		System.out.println("[*] file type: " + resume.getContentType());
		System.out.println("[*] file name: " + StringUtils.cleanPath(resume.getOriginalFilename()));

		Candidate candidate = candidateService.save(name, email, password, age, address, skills, notice, disability,
				resume);
		// creating resume download url
		String resumeUrl = "";
		resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/candidates/resume/")
				.path(candidate.getId().toString()).toUriString();

		CandidateResponseData response = new CandidateResponseData(candidate.getName(), candidate.getEmail(),
				candidate.getAge(), candidate.getAddress(), candidate.getSkills(), candidate.getNoticePeriod(),
				candidate.getDisability(), resumeUrl);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/resume/{id}")
	public ResponseEntity<Resource> downloadResume(@PathVariable Integer id) {
		Candidate candidate = candidateService.getCandidateById(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "resume; filename=\"" + candidate.getName() + " Resume.pdf\"")
				.body(new ByteArrayResource(candidate.getResume()));
	}

}
