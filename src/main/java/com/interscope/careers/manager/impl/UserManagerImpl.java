package com.interscope.careers.manager.impl;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManagerImpl implements UserManager {
    @Autowired
    private UsersService userService;

    @Override
    public UserResponseModel createUser(String name, String email, String password, Integer age, String address, Integer roleId, MultipartFile resume) {
        UserResponseModel response;

        if (roleId.equals(Roles.CANDIDATE.getRoleId())) {
            System.out.println("[*] file type: " + resume.getContentType());
            System.out.println("[*] file name: " + StringUtils.cleanPath(resume.getOriginalFilename()));

            User user = userService.save(name, email, new BCryptPasswordEncoder().encode(password), age, address, roleId, resume);
            // creating resume download url
            String resumeUrl = "";
            resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
                    .path(user.getId().toString()).toUriString();

            response = new CandidateResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), resumeUrl, user.getId());
        } else if (roleId.equals(Roles.RECRUITER.getRoleId())) {
            User user = userService.save(name, email, new BCryptPasswordEncoder().encode(password), age, address, roleId);

            response = new RecruiterResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), user.getId());
        } else
            response = null;
        return response;
    }

    @Override
    public UserResponseModel getCandidateById(Integer id) {
        User user = userService.getUsersById(id);

        UserResponseModel response;

        String resumeUrl = "NA";

        if (user.getRoleId().equals(Roles.CANDIDATE.getRoleId())) {
            resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
                    .path(user.getId().toString()).toUriString();

            response = new CandidateResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), resumeUrl, user.getId());
        } else {
            response = new RecruiterResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), user.getId());
        }
        return response;
    }

    @Override
    public List<RecruiterResponseModel> getRecruiters() {
        List<User> recruiters = userService.getUsersByRole(Roles.RECRUITER.getRoleId());

        List<RecruiterResponseModel> response = new ArrayList<>();
        recruiters.forEach(recruiter ->
                response.add(new RecruiterResponseModel(recruiter.getName(), recruiter.getEmail(), recruiter.getAge(),
                        recruiter.getAddress(), recruiter.getId()))
        );
        return response;
    }

    @Override
    public List<CandidateResponseModel> getCandidates() {
        List<User> candidates = userService.getUsersByRole(Roles.CANDIDATE.getRoleId());

        List<CandidateResponseModel> response = new ArrayList<>();
        candidates.forEach(candidate -> {
            String resumeUrl = "";
            resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
                    .path(candidate.getId().toString()).toUriString();
            response.add(new CandidateResponseModel(candidate.getName(), candidate.getEmail(), candidate.getAge(),
                    candidate.getAddress(), resumeUrl, candidate.getId()));
        });
        return response;
    }

    @Override
    public List<UserResponseModel> getUsers() {
        List<User> users = userService.getUsers();

        List<UserResponseModel> response = new ArrayList<>();
        users.forEach(user -> {
            if (user.getRoleId().equals(Roles.CANDIDATE.getRoleId())) {
                String resumeUrl = "";
                resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
                        .path(user.getId().toString()).toUriString();
                response.add(new CandidateResponseModel(user.getName(), user.getEmail(), user.getAge(),
                        user.getAddress(), resumeUrl, user.getId()));
            } else if (user.getRoleId().equals(Roles.RECRUITER.getRoleId())) {
                response.add(new RecruiterResponseModel(user.getName(), user.getEmail(), user.getAge(),
                        user.getAddress(), user.getId()));
            }
        });
        return response;
    }

    @Override
    public ResponseEntity<Resource> downloadResume(Integer id) {
        User candidate = userService.getUsersById(id);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "resume; filename=\"" + candidate.getName() + " Resume.pdf\"")
                .body(new ByteArrayResource(candidate.getResume()));
    }

    @Override
    public UserResponseModel getCandidateByEmail(String email) {
        User user = userService.getUserByEmail(email);

        UserResponseModel response;

        String resumeUrl = "NA";

        if (user.getRoleId().equals(Roles.CANDIDATE.getRoleId())) {
            resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
                    .path(user.getId().toString()).toUriString();

            response = new CandidateResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), resumeUrl, user.getId());
        } else {
            response = new RecruiterResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), user.getId());
        }
        return response;
    }
}
