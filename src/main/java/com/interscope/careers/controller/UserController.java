package com.interscope.careers.controller;

import com.interscope.careers.manager.UserManager;
import com.interscope.careers.model.CandidateResponseModel;
import com.interscope.careers.model.RecruiterResponseModel;
import com.interscope.careers.model.UserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@GetMapping("/users")
	public ResponseEntity<List<UserResponseModel>> getUsers() {
		return ResponseEntity.ok(userManager.getUsers());
	}

	// get all candidates
	@GetMapping("/candidates")
	public ResponseEntity<List<CandidateResponseModel>> getCandidates() {
		return ResponseEntity.ok(userManager.getCandidates());
	}

	// get all recruiters
	@GetMapping("/recruiters")
	public ResponseEntity<List<RecruiterResponseModel>> getRecruiters() {
		return ResponseEntity.ok(userManager.getRecruiters());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseModel> getCandidateById(@PathVariable Integer id) {
		return ResponseEntity.ok(userManager.getCandidateById(id));
	}

	// replace all the params with the user object
	@PostMapping
	public ResponseEntity<UserResponseModel> addUser(@RequestParam(name = "resume", required = false)
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
		return userManager.downloadResume(id);
	}

}
