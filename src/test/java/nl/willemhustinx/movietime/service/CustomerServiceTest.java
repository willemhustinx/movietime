package nl.willemhustinx.movietime.service;

import nl.willemhustinx.movietime.exception.NotFoundException;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.repository.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerService Test")
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setName("Willem");
        customer.setId(5);
        customerList.add(customer);

        Mockito.when(repository.findAll()).thenReturn(customerList);

        assertEquals(1, customerService.getAllCustomers().size());
        assertEquals(customer.getName(), customerService.getAllCustomers().get(0).getName());
    }

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setName("Willem");
        customer.setId(5);

        Mockito.when(repository.findByid(5L)).thenReturn(customer);

        assertEquals(customer.getName(), customerService.getCustomerById(5L).getName());
    }

    @Test
    public void getCustomerByIdNotFound() {
        Mockito.when(repository.findByid(5L)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> customerService.getCustomerById(5L));
    }

}
