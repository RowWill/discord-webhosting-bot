package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.Action;

@Repository
public interface ActionRepo extends JpaRepository<Action, Integer> {

}
