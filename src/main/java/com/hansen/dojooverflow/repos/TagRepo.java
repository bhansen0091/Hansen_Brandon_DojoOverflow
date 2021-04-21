package com.hansen.dojooverflow.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hansen.dojooverflow.models.Tag;

@Repository
public interface TagRepo extends CrudRepository<Tag, Long> {

	public List<Tag> findAll();
	public Optional <Tag> findBySubject(String subject);
	
}
