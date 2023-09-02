package com.treshermanitos.treshermanitos.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;

import com.treshermanitos.treshermanitos.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UsersPaginatedResponse getAllEntities(Pageable pageable) {
        Page<UserDTO> dataMapped = userRepository.findAllByStateIsTrue(pageable)
                .map(userDtoMapper);
        return UsersPaginatedResponse.builder()
                .users(dataMapped.getContent())
                .totalItems(dataMapped.getNumberOfElements())
                .totalPages(dataMapped.getTotalPages())
                .build();
    }

    public UserDTO getById(Long id) {
        User user = userRepository.findByIdAndStateIsTrue(id).orElseThrow(() -> new NotFoundException("User " + id +
                " " +
                "not " +
                "found"));
        return userDtoMapper.apply(user);

    }


    public UserDTO updateById(Long id, UserDTO body) {
        User user = userRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        if (body.getFirstName() != null && !body.getFirstName().isEmpty()) {
            user.setFirstName(body.getFirstName());
        }
        if (body.getLastName() != null && !body.getLastName().isEmpty()) {
            user.setLastName(body.getLastName());
        }

        if (body.getCity() != null && !body.getCity().isEmpty()) {
            user.setCity(body.getCity());
        }

        if (body.getAge() != null) {
            user.setAge(body.getAge());
        }
        userRepository.save(user);
        return userDtoMapper.apply(user);

    }

    public void deleteById(Long id) {
        User user = userRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        user.setState(false);
        userRepository.save(user);
    }

    public UserDTO createOne(UserDTO body) {
        throw new UnsupportedOperationException("Unimplemented method 'createOne'");
    }

}
