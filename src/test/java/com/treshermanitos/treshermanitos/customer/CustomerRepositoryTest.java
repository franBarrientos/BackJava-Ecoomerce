/* package com.treshermanitos.treshermanitos.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.UserDtoMapper;
import com.treshermanitos.treshermanitos.user.UserRepository;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserDtoMapper userDtoMapper;

    private CustomerDtoMapper customerDtoMapper;

    @BeforeEach
    void setup() {
        userDtoMapper = new UserDtoMapper();
        passwordEncoder = new BCryptPasswordEncoder();
        customerDtoMapper = new CustomerDtoMapper(userDtoMapper);

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldReturnCustomerMapped() {
        User user = User.builder()
                .firstName("fran")
                .lastName("barrietnos")
                .email("correo@correo.com")
                .password(passwordEncoder.encode("123"))
                .build();

        User userSaved = userRepository.save(user);

        Customer customerToSave = Customer.builder()
                .addres("JAMAICA")
                .dni(45645852)
                .user(userSaved)
                .build();

        Customer customerSaved = underTest.save(customerToSave);

        CustomerDTO expect = customerDtoMapper.apply(customerSaved);
        CustomerDTO result = underTest.findOneMapped(customerSaved.getId()).get();
        assertEquals(expect, result);
    }

    @Test
    void itShouldDoesntReturnCustomerMapped() {
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findOneMapped(2L).get();
        });

    }

    @Test
    void itShouldReturnAllCustomersMapped() {
        User user = User.builder()
                .firstName("fran")
                .lastName("barrietnos")
                .email("correo@correo.com")
                .password(passwordEncoder.encode("123"))
                .build();

        User userSaved = userRepository.save(user);

        Customer customerToSave = Customer.builder()
                .addres("JAMAICA")
                .dni(45645852)
                .user(userSaved)
                .build();

        underTest.save(customerToSave);
        List<CustomerDTO> customerDTOs = underTest.findAllMapped();
        assertEquals(1, customerDTOs.size());
        assertEquals(45645852, customerDTOs.get(0).getDni());
        assertNotNull(customerDTOs);
    }
}
 */