package nl.willemhustinx.movietime.controller;

import nl.willemhustinx.movietime.controller.dto.CustomerDTO;
import nl.willemhustinx.movietime.controller.mapper.CustomerMapper;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.service.CustomerService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerController Test")
public class CustomerControllerTest {
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Spy
    CustomerMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomers() {

        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setName("Willem");
        customerList.add(customer);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerService.getAllCustomers()).thenReturn(customerList);

        ResponseEntity<List<CustomerDTO>> responseEntity = customerController.getAllCustomers();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setName("Willem");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerService.getCustomerById(1L)).thenReturn(customer);

        ResponseEntity<CustomerDTO> responseEntity = customerController.getCustomerById(1L);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(customer.getName(), responseEntity.getBody().getName());
    }

}
