package nl.willemhustinx.movietime.repository;

import nl.willemhustinx.movietime.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByid(final Long CustomerId);
}
