package nl.willemhustinx.movietime.controller;

import nl.willemhustinx.movietime.controller.dto.CustomerDTO;
import nl.willemhustinx.movietime.controller.mapper.CustomerMapper;
import nl.willemhustinx.movietime.model.Customer;
import nl.willemhustinx.movietime.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerController(CustomerService customerServiceService, CustomerMapper mapper) {
        this.service = customerServiceService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> list = service.getAllCustomers();
        List<CustomerDTO> returnDTOList = list.stream().map(mapper::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(returnDTOList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("{customerID}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerID) {
        Customer customer = service.getCustomerById(customerID);
        CustomerDTO returnCustomer = mapper.convertToDTO(customer);
        return new ResponseEntity<>(returnCustomer, new HttpHeaders(), HttpStatus.OK);
    }
}
