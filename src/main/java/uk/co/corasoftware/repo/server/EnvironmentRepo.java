package uk.co.corasoftware.repo.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.server.Environment;

@Repository
public interface EnvironmentRepo extends JpaRepository<Environment, Integer> {

}