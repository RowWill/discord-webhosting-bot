package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.Reward;

@Repository
public interface RewardRepo extends JpaRepository<Reward, String> {

}
