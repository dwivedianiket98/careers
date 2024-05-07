package com.interscope.careers.repository;

import com.interscope.careers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<List<User>> findByRoleId(Integer roleid);

    Optional<User> findByEmail(String email);
}
