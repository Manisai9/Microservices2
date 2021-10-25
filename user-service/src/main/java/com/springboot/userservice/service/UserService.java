package com.springboot.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.userservice.entity.User;
import com.springboot.userservice.entity.UserRole;
import com.springboot.userservice.repository.UserRepository;
import com.springboot.userservice.repository.UserRoleRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

   
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

   
   
    public User saveUser(User user) {
        user.setActive(1);
        UserRole role = userRoleRepository.findUserRoleByRoleName("ROLE_USER");
        user.setRole(role);
        return userRepository.save(user);
    }
}
