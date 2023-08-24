package com.treshermanitos.treshermanitos.customer;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.treshermanitos.treshermanitos.user.UserDtoMapper;
import com.treshermanitos.treshermanitos.user.UserRepository;
import com.treshermanitos.treshermanitos.user.UserService;

@DataJpaTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    private CustomerService underTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerService(customerRepository, new UserService(userRepository, new UserDtoMapper()),
                new CustomerDtoMapper(new UserDtoMapper()));
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAll() {

        underTest.getAll();

        verify(customerRepository).findAllMapped();

    }

    @Test
    void canGetById() {

        Long customerId = 1L;
        CustomerDTO customer = new CustomerDTO();
        Mockito.when(customerRepository.findOneMapped(customerId)).thenReturn(java.util.Optional.of(customer));

        underTest.getById(1l);

        verify(customerRepository).findOneMapped(1l);
    }
}
