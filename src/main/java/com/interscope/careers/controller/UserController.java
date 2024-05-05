package com.interscope.careers.controller;

import com.interscope.careers.entity.Roles;
import com.interscope.careers.entity.User;
import com.interscope.careers.model.CandidateResponseModel;
import com.interscope.careers.model.RecruiterResponseModel;
import com.interscope.careers.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UsersService userService;

	// create the UserResponseModel which will be superclass to candidate and recruiter model
	@GetMapping("/users")
	public ResponseEntity<List<CandidateResponseModel>> getUsers() {
		List<User> users = userService.getUsers();

		List<CandidateResponseModel> response = new ArrayList<>();
		users.forEach(user -> {
			String resumeUrl = "";
			if(user.getRoleId() == Roles.CANDIDATE.getRoleId()) {
				resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
						.path(user.getId().toString()).toUriString();
			}
			response.add(new CandidateResponseModel(user.getName(), user.getEmail(), user.getAge(),
					user.getAddress(), resumeUrl));
		});
		return ResponseEntity.ok(response);
	}

	// get all candidates
	@GetMapping("/candidates")
	public ResponseEntity<List<CandidateResponseModel>> getCandidates() {
		List<User> candidates = userService.getUsersByRole(Roles.CANDIDATE.getRoleId());

		List<CandidateResponseModel> response = new ArrayList<>();
		candidates.forEach(candidate -> {
			String resumeUrl = "";
			resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
					.path(candidate.getId().toString()).toUriString();
			response.add(new CandidateResponseModel(candidate.getName(), candidate.getEmail(), candidate.getAge(),
					candidate.getAddress(), resumeUrl));
		});

		return ResponseEntity.ok(response);
	}

	// get all recruiters
	@GetMapping("/recruiters")
	public ResponseEntity<List<RecruiterResponseModel>> getRecruiters() {
		List<User> recruiters = userService.getUsersByRole(Roles.RECRUITER.getRoleId());

		List<RecruiterResponseModel> response = new ArrayList<>();
		recruiters.forEach(recruiter -> {
			response.add(new RecruiterResponseModel(recruiter.getName(), recruiter.getEmail(), recruiter.getAge(),
					recruiter.getAddress()));
		});

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CandidateResponseModel> getCandidateById(@PathVariable Integer id) {

		User user = userService.getUsersById(id);

		CandidateResponseModel response;

		String resumeUrl = "NA";

		if(user.getRoleId() == Roles.CANDIDATE.getRoleId()) {
			resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
					.path(user.getId().toString()).toUriString();
		}

		response = new CandidateResponseModel(user.getName(), user.getEmail(),
					user.getAge(), user.getAddress(), resumeUrl);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<CandidateResponseModel> addUser(
															  @RequestParam(name = "resume", required = false)
															  MultipartFile resume,
															  @RequestParam @Valid String name,
															  @RequestParam @Valid String email,
															  @RequestParam @Valid String password,
															  @RequestParam Integer age,
															  @RequestParam Integer roleId,
															  @RequestParam String address) {

		CandidateResponseModel response;
		if(roleId == Roles.CANDIDATE.getRoleId()) {
			System.out.println("[*] file type: " + resume.getContentType());
			System.out.println("[*] file name: " + StringUtils.cleanPath(resume.getOriginalFilename()));

			User user = userService.save(name, email, password, age, address, roleId, resume);
			// creating resume download url
			String resumeUrl = "";
			resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
					.path(user.getId().toString()).toUriString();

			response = new CandidateResponseModel(user.getName(), user.getEmail(),
					user.getAge(), user.getAddress(), resumeUrl);
		} else if(roleId == Roles.RECRUITER.getRoleId()) {
			User user = userService.save(name, email, password, age, address, roleId);

			response = new CandidateResponseModel(user.getName(), user.getEmail(),
					user.getAge(), user.getAddress(), null);
		} else
			response = null;

		return ResponseEntity.ok(response);
	}

	@GetMapping("/candidates/resume/{id}")
	public ResponseEntity<Resource> downloadResume(@PathVariable Integer id) {
		User candidate = userService.getUsersById(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "resume; filename=\"" + candidate.getName() + " Resume.pdf\"")
				.body(new ByteArrayResource(candidate.getResume()));
	}

}
