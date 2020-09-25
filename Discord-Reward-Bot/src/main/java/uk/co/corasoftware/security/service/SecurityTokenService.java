package uk.co.corasoftware.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.security.model.ApiToken;
import uk.co.corasoftware.security.repo.SecurityTokenRepo;
import uk.co.corasoftware.service.Service;

public class SecurityTokenService implements Service<ApiToken> {

	@Autowired
	private SecurityTokenRepo repo;

	@Override
	public ApiToken save(ApiToken entity) {
		return repo.save(entity);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public void delete(ApiToken entity) {
		repo.delete(entity);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<ApiToken> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public List<ApiToken> findAll() {
		return repo.findAll();
	}

	public Optional<ApiToken> findByToken(String token) {
		return repo.findByToken(token);
	}
}
