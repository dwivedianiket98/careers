package com.interscope.careers.repository;

import com.interscope.careers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    List<User> findByRoleid(Integer roleId);
    Optional<List<User>> findByRoleId(Integer roleid);
}
