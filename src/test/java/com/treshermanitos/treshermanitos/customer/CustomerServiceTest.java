/* package com.treshermanitos.treshermanitos.customer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.treshermanitos.treshermanitos.exceptions.RelationshipAlreadyExist;
import com.treshermanitos.treshermanitos.user.Role;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.UserDTO;
import com.treshermanitos.treshermanitos.user.UserDtoMapper;
import com.treshermanitos.treshermanitos.user.UserRepository;
import com.treshermanitos.treshermanitos.user.UserService;

@DataJpaTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
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

    @Test
    void canCreateOne() {
        User user = new User(1l, "fran", "barr", "fdsfs@gmail.com", "123", "ctes", 19, Role.ADMIN, "ctes",null, new Date(),
                new Date(), null);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        CustomerDTO customerDTO = new CustomerDTO();
        UserDTO user1 = new UserDTO();
        user1.setId(2L);
        customerDTO.setUser(user1);

        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer(1l,4556465,"fdfsdfsf","379402941",user,new Date(), new Date()));


        underTest.createOne(customerDTO);

        ArgumentCaptor<Customer> studenArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).save(studenArgumentCaptor.capture());
    }

    @Test
    void shouldThrowRelationshipAlreadyExistError() {
        Customer customer = new Customer();
        customer.setId(1l);

        User user = new User(1l, "fran", "barr", "fdsfs@gmail.com", "123", "ctes", 19, Role.ADMIN, "ctes",null, new Date(),
                new Date(), customer);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        CustomerDTO customerDTO = new CustomerDTO();
        UserDTO user1 = new UserDTO();
        user1.setId(2L);
        customerDTO.setUser(user1);

        assertThrows(RelationshipAlreadyExist.class, () -> {
            underTest.createOne(customerDTO);
        });

    }
}
 */