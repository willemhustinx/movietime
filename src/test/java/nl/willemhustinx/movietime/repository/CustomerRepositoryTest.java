package nl.willemhustinx.movietime.repository;

import nl.willemhustinx.movietime.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("CustomerRepository Test")
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAllCustomers() {
        Customer customer = new Customer();
        customer.setId(200L);
        customer.setName("Willem");
        entityManager.persist(customer);

        Customer customer2 = new Customer();
        customer2.setId(100L);
        customer2.setName("Henk");
        entityManager.persist(customer2);

        entityManager.flush();

        List<Customer> customersFound = customerRepository.findAll();

        assertEquals(2, customersFound.size());
    }

    @Test
    public void findAllCustomersEmpty() {
        List<Customer> customersFound = customerRepository.findAll();

        assertEquals(0, customersFound.size());
    }

    @Test
    public void findCustomerById() {
        Customer customer = new Customer();
        customer.setId(200L);
        customer.setName("Willem");
        entityManager.persist(customer);

        Customer customer2 = new Customer();
        customer2.setId(100L);
        customer2.setName("Henk");
        entityManager.persist(customer2);

        entityManager.flush();

        Customer customerFound = customerRepository.findByid(200L);

        assertEquals(customer.getName(), customerFound.getName());
    }

    @Test
    public void findCustomerByIdNotFound() {

        assertNull(customerRepository.findByid(200L));

    }

}
