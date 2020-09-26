package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.server.Node;

@Repository
public interface InstanceArgumentRepo extends JpaRepository<Node, Integer> {

}