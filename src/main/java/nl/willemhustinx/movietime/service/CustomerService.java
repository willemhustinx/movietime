package nl.willemhustinx.movietime.service;

import nl.willemhustinx.movietime.exception.NotFoundException;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long customerID) {
        Customer foundCustomer = repository.findByid(customerID);

        if (foundCustomer != null) {
            return foundCustomer;
        }
        throw new NotFoundException("Customer not found");
    }
}
