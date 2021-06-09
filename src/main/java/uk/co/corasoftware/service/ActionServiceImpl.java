package uk.co.corasoftware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.corasoftware.model.Action;
import uk.co.corasoftware.repo.ActionRepo;

@Service
public class ActionServiceImpl {

	@Autowired
	private ActionRepo repo;

	public Action save(Action entity) {
		return repo.save(entity);
	}

	public List<Action> findAll() {
		return repo.findAll();
	}
}
