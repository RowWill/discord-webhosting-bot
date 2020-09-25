package uk.co.corasoftware.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.security.model.ApiToken;

@Repository
public interface SecurityTokenRepo extends JpaRepository<ApiToken, String> {

	public Optional<ApiToken> findByToken(String token);
}
