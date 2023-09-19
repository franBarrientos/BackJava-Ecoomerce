package com.treshermanitos.application.service;

import com.treshermanitos.application.repository.UserRepository;
import com.treshermanitos.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAllUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public Optional<User> getUserIsActive(Long id){
        return this.userRepository.findUserIsActive(id);
    }



}
