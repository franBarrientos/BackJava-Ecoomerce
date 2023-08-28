package com.treshermanitos.treshermanitos.customer;

import java.util.List;

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

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customers = customerRepository.findAllMapped();
        return customers;
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
            throw new RelationshipAlreadyExist("cann't create a relationship one to one already exist!.");
        }
        Customer customer = Customer.builder()
                .addres(body.getAddres())
                .dni(body.getDni())
                .user(user)
                .build();
        Customer customerSaved = customerRepository.save(customer);
        return customerDtoMapper.apply(customerSaved);
    }

    @Override
    public CustomerDTO updateById(Long id, CustomerDTO body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Customer getByIdAllEntity(Long idLong) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByIdAllEntity'");
    }

    @Override
    public List<Customer> getAllEntities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEntities'");
    }

}
