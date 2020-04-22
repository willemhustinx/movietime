package nl.willemhustinx.movietime.controller.mapper;


import nl.willemhustinx.movietime.controller.dto.CustomerDTO;
import nl.willemhustinx.movietime.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Customer convertToNewEntity(final CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public CustomerDTO convertToDTO(final Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
