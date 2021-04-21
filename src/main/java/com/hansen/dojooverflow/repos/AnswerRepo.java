package com.hansen.dojooverflow.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hansen.dojooverflow.models.Answer;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Long> {

	public List<Answer> findAll();
	
}
