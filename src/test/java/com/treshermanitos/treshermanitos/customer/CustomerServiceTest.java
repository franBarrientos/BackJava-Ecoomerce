package com.treshermanitos.treshermanitos.customer;

import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.exceptions.RelationshipAlreadyExist;
import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.UserDTO;
import com.treshermanitos.treshermanitos.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService underTest;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CustomerDtoMapper customerDtoMapper;

    private Customer sharedCustomer;


    @BeforeEach
    void setUp() {
        this.sharedCustomer = Customer.builder()
                .id(1l)
                .dni(45645852)
                .phone("3794029441")
                .user(User.builder()
                        .id(1l)
                        .customer(this.sharedCustomer)
                        .build())
                .addres("jamaica")
                .build();

        Mockito.when(this.customerRepository.findActiveCustomer(1l)).thenReturn(Optional.of(this.sharedCustomer));

        Pageable pageable = PageRequest.of(0, 2);

        Page<Customer> customers = new PageImpl<>(List.of(this.sharedCustomer), pageable, 1);

        Mockito.when(this.customerRepository.findAllActiveCustomers(PageRequest.of(0, 2)))
                .thenReturn(customers);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        CustomersPaginatedResponse expect = CustomersPaginatedResponse.builder()
                .customers(List.of(this.customerDtoMapper.apply(this.sharedCustomer)))
                .totalItems(1)
                .totalPages(2)
                .build();

        CustomersPaginatedResponse result = this.underTest.getAll(PageRequest.of(0, 2));
        assertEquals(expect, result);
    }

    @Test
    void getById() {
        CustomerDTO result = this.underTest.getById(this.sharedCustomer.getId());
        assertEquals(this.customerDtoMapper.apply(this.sharedCustomer), result);
        /*        assertEquals(1l, result.getId());*/
    }


    @Test
    public void testCreateOne() {
        // Create a sample CustomerRequest
        CustomerRequest request = new CustomerRequest();
        request.setUser(this.sharedCustomer.getUser().getId()); // Set a valid user ID
        request.setAddres(this.sharedCustomer.getAddres());
        request.setDni(this.sharedCustomer.getDni());

        // Mock the behavior of userRepository
        User mockUser = new User();
        mockUser.setId(1L);
        Mockito.when(
                        this.userRepository.findByIdAndStateIsTrue(1L))
                .thenReturn(Optional.of(mockUser));

        // Mock the behavior of customerRepository
        Mockito.when(this.customerRepository.save(Mockito.any(Customer.class))).thenAnswer(invocation -> {
            Customer savedCustomer = invocation.getArgument(0);
            savedCustomer.setId(1L); // Set an ID for the saved customer
            return savedCustomer;
        });


        // Call the createOne method with the sample request
        CustomerDTO result = this.underTest.createOne(request);

        // Assert the result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(request.getAddres(), result.getAddres());
        assertEquals(request.getDni(), result.getDni());
    }

    @Test()
    public void testCreateOneUserNotFound() {
        // Create a sample CustomerRequest with a user that doesn't exist
        CustomerRequest request = new CustomerRequest();
        request.setUser(999L); // Set an invalid user ID

        // Mock the behavior of userRepository to return an empty Optional
        Mockito.when(userRepository.findByIdAndStateIsTrue(999L)).thenReturn(Optional.empty());

        // Call the createOne method with the sample request
        assertThrows(NotFoundException.class,
                () -> this.underTest.createOne(request)
        );
    }

    @Test()
    public void testCreateOneRelationshipAlreadyExists() {
        // Create a sample CustomerRequest with a user that already has a customer
        CustomerRequest request = new CustomerRequest();
        request.setUser(1L); // Set a valid user ID

        // Mock the behavior of userRepository to return a user with an existing customer
        User userWithExistingCustomer = new User();
        userWithExistingCustomer.setId(1L);
        userWithExistingCustomer.setCustomer(new Customer()); // Set an existing customer
        Mockito.when(userRepository.findByIdAndStateIsTrue(1L)).thenReturn(Optional.of(userWithExistingCustomer));

        // Call the createOne method with the sample request
        assertThrows(RelationshipAlreadyExist.class,
                () -> this.underTest.createOne(request)
        );

    }


    @Test
    public void updateById() {
        // Create a sample CustomerDTO with updated addres and phone
        CustomerDTO updatedCustomerDTO = CustomerDTO.builder()
                .addres("jamaica")
                .phone("3794029441")
                .build();

        // Mock the behavior of customerRepository
        Customer existingCustomer = new Customer();
        existingCustomer.setId(1L);
        Mockito.when(this.customerRepository.findActiveCustomer(1L)).thenReturn(Optional.of(existingCustomer));

        Mockito.when(this.customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(Customer.builder()
                        .id(1l)
                        .addres("jamaica")
                        .phone("3794029441")
                        .user(User.builder()
                                .id(1l)
                                .build())
                        .build());

        // Call the updateById method with the sample inputs
        CustomerDTO result = this.underTest.updateById(1L, updatedCustomerDTO);

        // Assert the result
        assertNotNull(result);
        assertEquals("jamaica", result.getAddres());
        assertEquals("3794029441", result.getPhone());
        // Assert that DNI remains unchanged
        assertNull(result.getDni());
    }

    @Test()
    public void testUpdateByIdCustomerNotFound() {
        // Mock the behavior of customerRepository to return an empty Optional
        Mockito.when(this.customerRepository.findActiveCustomer(111L)).thenReturn(Optional.empty());

        // Call the updateById method with a non-existing customer ID
        assertThrows(NotFoundException.class,
                ()->this.underTest.updateById(111L, new CustomerDTO())
                );

        // This should throw a NotFoundException
    }
}