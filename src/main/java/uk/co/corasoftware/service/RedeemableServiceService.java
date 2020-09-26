package uk.co.corasoftware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.model.RedeemableService;
import uk.co.corasoftware.repo.RedeemableInstanceRepo;

public class RedeemableServiceService implements IService<RedeemableService> {

	@Autowired
	private RedeemableInstanceRepo repo;

	@Override
	public RedeemableService save(RedeemableService entity) {
		return repo.save(entity);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public void delete(RedeemableService entity) {
		repo.delete(entity);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<RedeemableService> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public List<RedeemableService> findAll() {
		return repo.findAll();
	}

}
