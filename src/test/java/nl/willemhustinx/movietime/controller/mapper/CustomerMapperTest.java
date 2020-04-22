package nl.willemhustinx.movietime.controller.mapper;

import nl.willemhustinx.movietime.controller.dto.CustomerDTO;
import nl.willemhustinx.movietime.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CustomerMapper Test")
public class CustomerMapperTest {

    private static CustomerMapper mapper;

    @BeforeAll
    public static void init() {
        mapper = new CustomerMapper();
    }

    @Test
    public void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(200L);
        customer.setName("customerToCustomerDTO");
        Map<String, String> interests = new HashMap<>();
        interests.put("runtime", "< 125 minutes");
        customer.setInterests(interests);

        CustomerDTO customerDTO = mapper.convertToDTO(customer);

        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals(customer.getInterests(), customerDTO.getInterests());
    }

    @Test
    public void customerDTOToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(200L);
        customerDTO.setName("CustomerDTOToCustomer");
        Map<String, String> interests = new HashMap<>();
        interests.put("runtime", "< 180 minutes");
        customerDTO.setInterests(interests);

        Customer customer = mapper.convertToNewEntity(customerDTO);

        assertEquals(customerDTO.getName(), customer.getName());
        assertEquals(customerDTO.getInterests(), customer.getInterests());
    }
}
