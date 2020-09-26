package uk.co.corasoftware.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.RedeemableService;

@Repository
public interface RedeemableInstanceRepo extends JpaRepository<RedeemableService, String> {

}