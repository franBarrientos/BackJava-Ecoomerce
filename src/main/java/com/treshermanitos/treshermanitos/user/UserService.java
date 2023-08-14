package com.treshermanitos.treshermanitos.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.config.BaseService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements BaseService<User, UserDTO> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDtoMapper userDtoMapper;

    @Override
    public List<UserDTO> getAll() {
        var users = userRepository.findAll().stream().map(userDtoMapper).collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new RuntimeException("Users  not found");
        }
        return users;
    }

    @Override
    public UserDTO getById(Long id) {
        return userDtoMapper.apply(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User " + id + " not found")));
    }

    @Override
    public void createOne(UserDTO body) {
    }

    @Override
    public UserDTO updateById(Long id, UserDTO body) {
        User optionalUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User " + id + " not found"));
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
        if (body.getAge()!= null) {
            optionalUser.setAge(body.getAge());
        }
        userRepository.save(optionalUser);
        return userDtoMapper.apply(optionalUser);

    }

    @Override
    public UserDTO deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
