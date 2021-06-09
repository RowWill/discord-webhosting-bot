package uk.co.corasoftware.repo.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.co.corasoftware.model.security.ApiToken;

@Repository
public interface SecurityTokenRepo extends JpaRepository<ApiToken, String> {

	public Optional<ApiToken> findByToken(String token);

	public Optional<ApiToken> findByIssuedTo(String name);
}
