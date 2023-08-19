package com.treshermanitos.treshermanitos.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.config.BaseService;
import com.treshermanitos.treshermanitos.exceptions.UserNotFoundException;

@Service
public class UserService implements BaseService<User, UserDTO> {

    private final UserRepository userRepository;

    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public List<UserDTO> getAll() {
        var users = userRepository.findAll().stream().map(userDtoMapper).collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users  not found");
        }
        return users;
    }

    @Override
    public UserDTO getById(Long id) {
        return userDtoMapper.apply(
                userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User " + id + " not found")));
    }

    @Override
    public void createOne(UserDTO body) {
    }

    @Override
    public UserDTO updateById(Long id, UserDTO body) {
        User optionalUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
        if (body.getFirstName() != null && !body.getFirstName().isEmpty()) {
            optionalUser.setFirstName(body.getFirstName());
        }
        if (body.getLastName() != null && !body.getLastName().isEmpty()) {
            optionalUser.setLastName(body.getLastName());
        }

        if (body.getCity() != null && !body.getCity().isEmpty()) {
            optionalUser.setCity(body.getCity());
        }
        if (body.getProvince() != null && !body.getProvince().isEmpty()) {
            optionalUser.setProvince(body.getProvince());
        }
        if (body.getAge() != null) {
            optionalUser.setAge(body.getAge());
        }
        userRepository.save(optionalUser);
        return userDtoMapper.apply(optionalUser);

    }

    @Override
    public Boolean deleteById(Long id) {
        User optionalUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
        userRepository.deleteById(optionalUser.getId());
        return true;
    }

}
