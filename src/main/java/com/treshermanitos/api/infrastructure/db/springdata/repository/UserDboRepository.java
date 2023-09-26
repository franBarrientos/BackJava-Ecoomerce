package com.treshermanitos.api.infrastructure.db.springdata.repository;

import java.util.Optional;

import com.treshermanitos.api.application.repository.UserRepository;
import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.db.springdata.entities.UserEntity;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.UserEntityMapper;
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
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        return user.isPresent()?
                Optional.of(userEntityMapper.toDomain(user.get()))
                :
                Optional.empty();

    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        return user.isPresent()?
                Optional.of(userEntityMapper.toDomain(user.get()))
                :
                Optional.empty();
    }

    @Override
    public User save(User user) {

        return this.userEntityMapper.toDomain(
                this.userRepository.save(this.userEntityMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findUserIsActive(Long id) {
        Optional<UserEntity> user = this.userRepository.findByIdAndStateIsTrue(id);
        return user.isPresent()?
                Optional.of(userEntityMapper.toDomain(user.get()))
                :
                Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findByIdAndStateIsTrue(id);
        if (userEntity.isPresent()){
            User user = this.userEntityMapper.toDomain(userEntity.get());
            user.setState(false);
            this.userRepository.save(this.userEntityMapper.toEntity(user));
            return true;
        }else {
            return false;
        }

    }
}
  /*  Optional<User> findByEmail(String email);

    Optional<User> findByIdAndStateIsTrue(Long id);

    @Query("SELECT new com.treshermanitos.api.user.dto.UserDTO( " +
            "u.id, u.firstName, u.lastName, u.email, u.city, u.age, " +
            "u.province, u.customer.id, u.customer.dni, u.customer.addres, " +
            "u.customer.phone) FROM User u LEFT JOIN u.customer WHERE u.state = true ")
    Page<UserDTO> findAllUsersAsDtos(Pageable pageable);*/

