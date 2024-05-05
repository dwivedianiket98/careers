package com.interscope.careers.service;

import com.interscope.careers.entity.User;
import com.interscope.careers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

//	insert into users values(10001, 'jhandha tola', 23, 'No', 'gandhi@gmail.com', 'Gandhi Boi', 30, 'admin@123', 'not available', 'sleeping');

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public List<User> getUsersByRole(Integer roleId) {
		Optional<List<User>> userListByRoleOptional = userRepository.findByRoleId(roleId);
		if (!userListByRoleOptional.isPresent()) {
			new Exception("Id not found.");
		}
		return userListByRoleOptional.get();
	}

	@Override
	public User save(String name, String email, String password, Integer age, String address, Integer roleId, MultipartFile resume) {
		User user = null;
		try {
			user = new User(name, email, password, age, address, roleId, resume.getBytes());
		} catch (IOException e) {
			System.out.println("[*] error while saving users");
		}
		return userRepository.save(user);
	}

	@Override
	public User save(String name, String email, String password, Integer age, String address, Integer roleId) {
		User user = null;
		try {
			user = new User(name, email, password, age, address, roleId);
		} catch (Exception e) {
			System.out.println("[*] error while saving users");
		}
		return userRepository.save(user);
	}

	@Override
	public User getUsersById(Integer id) {

		Optional<User> usersOptional = userRepository.findById(id);

		if (!usersOptional.isPresent()) {
			new Exception("Id not found.");
		}

		return usersOptional.get();
	}
}
