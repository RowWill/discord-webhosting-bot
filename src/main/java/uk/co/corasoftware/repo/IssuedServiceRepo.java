package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.IssuedService;

@Repository
public interface IssuedServiceRepo extends JpaRepository<IssuedService, Integer> {

}