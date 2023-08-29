package com.treshermanitos.treshermanitos.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.config.BaseService;
import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.exceptions.RelationshipAlreadyExist;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService implements BaseService<Customer, CustomerDTO> {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final CustomerDtoMapper customerDtoMapper;

    public CustomersPaginatedResponse getAll(Pageable pageable) {
        Page<Customer> data = customerRepository.findAll(pageable);
        return CustomersPaginatedResponse.builder()
                .customers(data.getContent())
                .totalItems(data.getNumberOfElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    public Customer getByIdAllEntity(Long idLong) {
        return customerRepository.findById(idLong).orElseThrow(()->new NotFoundException("user "+idLong+" not found"));
    }

    @Override
    public CustomerDTO getById(Long idLong) {
        return customerRepository.findOneMapped(idLong)
                .orElseThrow(() -> new NotFoundException(" customer" + idLong + " not found"));
    }

    @Override

    public CustomerDTO createOne(CustomerDTO body) {
        User user = userService.getByIdAllEntity(body.getUser().getId());
        if (user.getCustomer() != null) {
            throw new RelationshipAlreadyExist("can not create a relationship one to one already exist!.");
        }
        Customer customer = Customer.builder()
                .addres(body.getAddres())
                .dni(body.getDni())
                .user(user)
                .build();
        Customer customerSaved = customerRepository.save(customer);
        return customerDtoMapper.apply(customerSaved);
    }

    public CustomerDTO updateById(Long id, CustomerDTO body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
