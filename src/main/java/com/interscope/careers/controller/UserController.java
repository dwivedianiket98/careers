package com.interscope.careers.controller;

import com.interscope.careers.entity.Roles;
import com.interscope.careers.entity.User;
import com.interscope.careers.manager.UserManager;
import com.interscope.careers.model.CandidateResponseModel;
import com.interscope.careers.model.RecruiterResponseModel;
import com.interscope.careers.model.UserResponseModel;
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

	@Autowired
	private UserManager userManager;

	@GetMapping("/users")
	public ResponseEntity<List<UserResponseModel>> getUsers() {
		List<User> users = userService.getUsers();

		List<UserResponseModel> response = new ArrayList<>();
		users.forEach(user -> {
			if(user.getRoleId().equals(Roles.CANDIDATE.getRoleId())) {
				String resumeUrl = "";
				resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
						.path(user.getId().toString()).toUriString();
				response.add(new CandidateResponseModel(user.getName(), user.getEmail(), user.getAge(),
						user.getAddress(), resumeUrl, user.getId()));
			} else if(user.getRoleId().equals(Roles.RECRUITER.getRoleId())) {
				response.add(new RecruiterResponseModel(user.getName(), user.getEmail(), user.getAge(),
						user.getAddress(), user.getId()));
			}
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
					candidate.getAddress(), resumeUrl, candidate.getId()));
		});

		return ResponseEntity.ok(response);
	}

	// get all recruiters
	@GetMapping("/recruiters")
	public ResponseEntity<List<RecruiterResponseModel>> getRecruiters() {
		List<User> recruiters = userService.getUsersByRole(Roles.RECRUITER.getRoleId());

		List<RecruiterResponseModel> response = new ArrayList<>();
		recruiters.forEach(recruiter ->
			response.add(new RecruiterResponseModel(recruiter.getName(), recruiter.getEmail(), recruiter.getAge(),
					recruiter.getAddress(), recruiter.getId()))
		);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseModel> getCandidateById(@PathVariable Integer id) {

		User user = userService.getUsersById(id);

		UserResponseModel response;

		String resumeUrl = "NA";

		if(user.getRoleId().equals(Roles.CANDIDATE.getRoleId())) {
			resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
					.path(user.getId().toString()).toUriString();

			response = new CandidateResponseModel(user.getName(), user.getEmail(),
					user.getAge(), user.getAddress(), resumeUrl, user.getId());
		} else {
			response = new RecruiterResponseModel(user.getName(), user.getEmail(),
					user.getAge(), user.getAddress(), user.getId());
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<UserResponseModel> addUser(
															  @RequestParam(name = "resume", required = false)
															  MultipartFile resume,
															  @RequestParam @Valid String name,
															  @RequestParam @Valid String email,
															  @RequestParam @Valid String password,
															  @RequestParam Integer age,
															  @RequestParam Integer roleId,
															  @RequestParam String address) {

		return ResponseEntity.ok(userManager.createUser(name, email, password, age, address, roleId, resume));
	}

	@GetMapping("/candidates/resume/{id}")
	public ResponseEntity<Resource> downloadResume(@PathVariable Integer id) {
		User candidate = userService.getUsersById(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "resume; filename=\"" + candidate.getName() + " Resume.pdf\"")
				.body(new ByteArrayResource(candidate.getResume()));
	}

}
