package com.interscope.careers.manager;

import com.interscope.careers.model.CandidateResponseModel;
import com.interscope.careers.model.RecruiterResponseModel;
import com.interscope.careers.model.UserResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserManager {
    UserResponseModel createUser(String name, String email, String password, Integer age, String address, Integer roleId, MultipartFile resume);

    UserResponseModel getCandidateById(Integer id);

    List<RecruiterResponseModel> getRecruiters();

    List<CandidateResponseModel> getCandidates();

    List<UserResponseModel> getUsers();

    ResponseEntity<Resource> downloadResume(Integer id);
}
