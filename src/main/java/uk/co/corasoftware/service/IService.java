package uk.co.corasoftware.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
	public T save(T entity);

	public void deleteAll();

	public void delete(T entity);

	public void deleteById(String id);

	public Optional<T> findById(String id);

	public List<T> findAll();

}