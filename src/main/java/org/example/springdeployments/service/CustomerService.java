package org.example.springdeployments.service;

import org.example.springdeployments.entity.Customer;
import org.example.springdeployments.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }
    public Customer createCustomer(Customer customer) {
        System.out.println("Creating customer: " + customer.getFirstName());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        System.out.println(customer.getId());
        Optional<Customer> byId = customerRepository.findById(customer.getId());
        byId.ifPresent(existingCustomer -> {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
        });
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
