package uk.co.corasoftware.repo.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.server.Node;

@Repository
public interface NodeRepo extends JpaRepository<Node, Integer> {

}