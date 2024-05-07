package com.interscope.careers.service;

import com.interscope.careers.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {

    List<User> getUsers();

    List<User> getUsersByRole(Integer roleId);


    User save(String name, String email, String password, Integer age, String address, Integer roleId, MultipartFile file);

    User save(String name, String email, String password, Integer age, String address, Integer roleId);

    User getUsersById(Integer id);

    User getUserByEmail(String email);
}
