package uk.co.corasoftware.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.service.Service;

public class Controller<T> implements Service<T> {

	@Autowired
	private Service<T> service;

	@Override
	public T save(T entity) {
		return service.save(entity);
	}

	@Override
	public void deleteAll() {
		service.deleteAll();
	}

	@Override
	public void delete(T entity) {
		service.delete(entity);
	}

	@Override
	public void deleteById(String id) {
		service.deleteById(id);
	}

	@Override
	public Optional<T> findById(String id) {
		return service.findById(id);
	}

	@Override
	public List<T> findAll() {
		return service.findAll();
	}

}
