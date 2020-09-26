package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

}
