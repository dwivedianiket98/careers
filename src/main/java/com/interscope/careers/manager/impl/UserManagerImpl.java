package com.interscope.careers.manager.impl;

import com.interscope.careers.entity.Roles;
import com.interscope.careers.entity.User;
import com.interscope.careers.manager.UserManager;
import com.interscope.careers.model.CandidateResponseModel;
import com.interscope.careers.model.RecruiterResponseModel;
import com.interscope.careers.model.UserResponseModel;
import com.interscope.careers.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UserManagerImpl implements UserManager {
    @Autowired
    private UsersService userService;

    @Override
    public UserResponseModel createUser(String name, String email, String password, Integer age, String address, Integer roleId, MultipartFile resume) {
        UserResponseModel response;

        if(roleId.equals(Roles.CANDIDATE.getRoleId())) {
            System.out.println("[*] file type: " + resume.getContentType());
            System.out.println("[*] file name: " + StringUtils.cleanPath(resume.getOriginalFilename()));

            User user = userService.save(name, email, password, age, address, roleId, resume);
            // creating resume download url
            String resumeUrl = "";
            resumeUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("user/candidates/resume/")
                    .path(user.getId().toString()).toUriString();

            response = new CandidateResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), resumeUrl, user.getId());
        } else if(roleId.equals(Roles.RECRUITER.getRoleId())) {
            User user = userService.save(name, email, password, age, address, roleId);

            response = new RecruiterResponseModel(user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress(), user.getId());
        } else
            response = null;
        return response;
    }
}
