package com.interscope.careers.manager;

import com.interscope.careers.model.UserResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface UserManager {
    UserResponseModel createUser(String name, String email, String password, Integer age, String address, Integer roleId, MultipartFile resume);
}
