package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.repository.UserRepository;
import com.treshermanitos.api.domain.User;
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

    public User getUserIsActive(Long id) {
        return this.userRepository
                .findUserIsActive(id)
                .orElseThrow(()->new NotFoundException("user "+id+" not found"));
    }

    public void deleteById(Long id) {
        User user = this.userRepository.findUserIsActive(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        user.setState(false);
        userRepository.save(user);
    }



}
