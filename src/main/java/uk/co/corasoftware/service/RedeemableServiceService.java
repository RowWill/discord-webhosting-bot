package uk.co.corasoftware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.model.ServiceProduct;
import uk.co.corasoftware.repo.ServiceProductRepo;

public class RedeemableServiceService implements IService<ServiceProduct> {

	@Autowired
	private ServiceProductRepo repo;

	@Override
	public ServiceProduct save(ServiceProduct entity) {
		return repo.save(entity);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public void delete(ServiceProduct entity) {
		repo.delete(entity);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<ServiceProduct> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public List<ServiceProduct> findAll() {
		return repo.findAll();
	}

}
