package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.ServiceProduct;

@Repository
public interface ServiceProductRepo extends JpaRepository<ServiceProduct, String> {

}