package com.treshermanitos.treshermanitos.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.treshermanitos.treshermanitos.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.treshermanitos.treshermanitos.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;
    @Autowired
    private UserRepository userRepository;

    private User sharedUser;
    private Customer sharedCustomer;


    @BeforeEach
    void setup() {
        User user = User.builder()
                .firstName("fran")
                .lastName("barrietnos")
                .email("correo@correo.com")
                .password("123")
                .build();

        this.sharedUser = this.userRepository.save(user);

        Customer customer = Customer.builder()
                .addres("JAMAICA")
                .dni(45645852)
                .user(this.sharedUser)
                .build();

        this.sharedCustomer = this.underTest.save(customer);
    }

    @AfterEach
    void tearDown() {
        this.underTest.findAll();
        this.userRepository.deleteAll();
    }


    @Test
    void findActiveCustomerCaseFound() {
        Customer result = this.underTest.findActiveCustomer(this.sharedCustomer.getId()).get();
        assertEquals(45645852, result.getDni());
    }

    @Test
    void findActiveCustomerCaseNotFound() {
        assertThrows(NoSuchElementException.class, () -> this.underTest.findActiveCustomer(11l).get());
    }

    @Test
    void findActiveCustomerCaseUserBlocked() {
        User user = this.underTest.findActiveCustomer(this.sharedCustomer.getId()).get().getUser();
        user.setState(false);
        this.userRepository.save(user);

        assertThrows(NoSuchElementException.class, () -> this.underTest.findActiveCustomer(this.sharedCustomer.getId()).get());
    }

    @Test
    void findAllActiveCustomers() {
        Pageable pageable = PageRequest.of(0, 15);
        Page<Customer> result = this.underTest.findAllActiveCustomers(pageable);

        assertEquals(0, result.getNumber());
        assertEquals(15, result.getSize());
        assertEquals(1, result.getNumberOfElements());
    }

}
