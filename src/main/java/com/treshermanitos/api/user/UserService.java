package com.treshermanitos.api.user;

import com.treshermanitos.infrastructure.db.springdata.entities.UserEntity;
import com.treshermanitos.infrastructure.db.springdata.repository.UserRepository;
import com.treshermanitos.infrastructure.rest.spring.dto.UserDTO;
import com.treshermanitos.infrastructure.db.springdata.mapper.UserEntityMapper;
import com.treshermanitos.infrastructure.rest.spring.response.UsersPaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;

import com.treshermanitos.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityMapper userDtoMapper;

    public UsersPaginatedResponse getAll(Pageable pageable) {
        Page<UserDTO> dataMapped = userRepository.findAllUsersAsDtos(pageable);

        return UsersPaginatedResponse.builder()
                .users(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }

    public UserDTO getById(Long id) {
        UserEntity userEntity = userRepository.findByIdAndStateIsTrue(id).orElseThrow(() -> new NotFoundException("User " + id +
                " " +
                "not " +
                "found"));
        return userDtoMapper.apply(userEntity);

    }


    public UserDTO updateById(Long id, UserDTO body) {
        UserEntity userEntity = userRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        if (body.getFirstName() != null && !body.getFirstName().isEmpty()) {
            userEntity.setFirstName(body.getFirstName());
        }
        if (body.getLastName() != null && !body.getLastName().isEmpty()) {
            userEntity.setLastName(body.getLastName());
        }

        if (body.getCity() != null && !body.getCity().isEmpty()) {
            userEntity.setCity(body.getCity());
        }

        if (body.getAge() != null) {
            userEntity.setAge(body.getAge());
        }
        userRepository.save(userEntity);
        return userDtoMapper.apply(userEntity);

    }

    public void deleteById(Long id) {
        UserEntity userEntity = userRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        userEntity.setState(false);
        userRepository.save(userEntity);
    }

    public UserDTO createOne(UserDTO body) {
        throw new UnsupportedOperationException("Unimplemented method 'createOne'");
    }

}
