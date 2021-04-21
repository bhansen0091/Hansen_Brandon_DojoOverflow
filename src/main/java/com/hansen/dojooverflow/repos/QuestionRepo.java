package com.hansen.dojooverflow.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hansen.dojooverflow.models.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long> {
	
	public List<Question> findAll();

}
