package uk.co.corasoftware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.model.Reward;
import uk.co.corasoftware.repo.RewardRepo;

public class RewardService implements Service<Reward> {

	@Autowired
	private RewardRepo repo;

	@Override
	public Reward save(Reward entity) {
		return repo.save(entity);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public void delete(Reward entity) {
		repo.delete(entity);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<Reward> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public List<Reward> findAll() {
		return repo.findAll();
	}

}
