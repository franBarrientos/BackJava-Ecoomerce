package com.treshermanitos.treshermanitos.user;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;

import com.treshermanitos.treshermanitos.config.BaseService;
import com.treshermanitos.treshermanitos.exceptions.NotFoundException;

@Service
public class UserService implements BaseService<User, UserDTO>{

    private final UserRepository userRepository;

    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }


    public UsersPaginatedResponse getAllEntities(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        return UsersPaginatedResponse.builder()
                .users(data.getContent())
                .totalItems(data.getNumberOfElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    public UserDTO getById(Long id) {
        return userRepository.findByIdMapped(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    public User getByIdAllEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    public UserDTO updateById(Long id, UserDTO body) {
        User optionalUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        if (body.getFirstName() != null && !body.getFirstName().isEmpty()) {
            optionalUser.setFirstName(body.getFirstName());
        }
        if (body.getLastName() != null && !body.getLastName().isEmpty()) {
            optionalUser.setLastName(body.getLastName());
        }

        if (body.getCity() != null && !body.getCity().isEmpty()) {
            optionalUser.setCity(body.getCity());
        }

        if (body.getAge() != null) {
            optionalUser.setAge(body.getAge());
        }
        userRepository.save(optionalUser);
        return userDtoMapper.apply(optionalUser);

    }

    public void deleteById(Long id) {
        User optionalUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        optionalUser.setState(false);
        userRepository.save(optionalUser);
    }

    public UserDTO createOne(UserDTO body) {
        throw new UnsupportedOperationException("Unimplemented method 'createOne'");
    }

}
