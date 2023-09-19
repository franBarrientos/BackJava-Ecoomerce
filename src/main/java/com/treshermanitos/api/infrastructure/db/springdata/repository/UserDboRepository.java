package com.treshermanitos.infrastructure.db.springdata.repository;

import java.util.Optional;

import com.treshermanitos.application.repository.UserRepository;
import com.treshermanitos.domain.User;
import com.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.infrastructure.db.springdata.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDboRepository implements UserRepository {

    private final SpringDataUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable)
                .map(userEntityMapper::apply);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User findByEmail(String email) {
        return this.userEntityMapper.apply(this.userRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("not found")));
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findUserIsActive(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
  /*  Optional<User> findByEmail(String email);

    Optional<User> findByIdAndStateIsTrue(Long id);

    @Query("SELECT new com.treshermanitos.api.user.dto.UserDTO( " +
            "u.id, u.firstName, u.lastName, u.email, u.city, u.age, " +
            "u.province, u.customer.id, u.customer.dni, u.customer.addres, " +
            "u.customer.phone) FROM User u LEFT JOIN u.customer WHERE u.state = true ")
    Page<UserDTO> findAllUsersAsDtos(Pageable pageable);*/

