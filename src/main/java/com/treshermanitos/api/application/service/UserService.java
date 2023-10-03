package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.dto.UserDTO;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.mapper.UserDtoMapper;
import com.treshermanitos.api.application.repository.UserRepository;
import com.treshermanitos.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;


    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return this.userRepository
                .findAll(pageable)
                .map(userDtoMapper::toDto);
    }

    public UserDTO getUserIsActive(Long id) {
        return this.userDtoMapper
                .toDto(this.userRepository
                .findUserIsActive(id)
                .orElseThrow(() -> new NotFoundException("user " + id + " not found")));
    }

    public String deleteById(Long id) {
        User user = this.userRepository.findUserIsActive(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        user.setState(false);
        userRepository.save(user);
        return "user " + id + " deleted";
    }


    public UserDTO updateById(long id, UserDTO body) {
        Optional<User> userUpdated = this.userRepository
                .updateById(id, this.userDtoMapper.toDomain(body));

        if (userUpdated.isEmpty()){
            throw new NotFoundException("user " + id + " not found");

        }else {
            return this.userDtoMapper
                    .toDto(userUpdated.get());
        }
    }
}
