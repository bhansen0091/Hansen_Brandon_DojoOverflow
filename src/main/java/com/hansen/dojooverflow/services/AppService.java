package com.hansen.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hansen.dojooverflow.models.Answer;
import com.hansen.dojooverflow.models.Question;
import com.hansen.dojooverflow.models.Tag;
import com.hansen.dojooverflow.repos.AnswerRepo;
import com.hansen.dojooverflow.repos.QuestionRepo;
import com.hansen.dojooverflow.repos.TagRepo;

@Service
public class AppService {

	private final QuestionRepo qRepo;
	private final AnswerRepo aRepo;
	private final TagRepo tRepo;
	
	public AppService(QuestionRepo qRepo, AnswerRepo aRepo, TagRepo tRepo) {
		super();
		this.qRepo = qRepo;
		this.aRepo = aRepo;
		this.tRepo = tRepo;
	}
	
	public Question createQuestion(Question newQuestion) {
		
		System.out.println(newQuestion.getParsedTag());
		System.out.println(newQuestion.splitTags());
		//add tag to db
		ArrayList<Tag> questionTags = new ArrayList<Tag>();
		for (String subject : newQuestion.splitTags()) {
//			check is incoming tag is in db
			Tag tagToBeAdded = this.tRepo.findBySubject(subject).orElse(null);
//			if new tag not in db, add it
			if (tagToBeAdded == null) {
				tagToBeAdded = new Tag(subject);
				this.tRepo.save(tagToBeAdded);
			}
//			add tag to questionTags list
			if (!questionTags.contains(tagToBeAdded)) {
				questionTags.add(tagToBeAdded);
			}
		}		
//		Setting attr "tags (Tag List) from the instance newQuestion as questionTags
		newQuestion.setTags(questionTags);			
//		Adding new question with related tags to db			
		return this.qRepo.save(newQuestion);
	}
	
	//Create an answer.
	public Answer createAnswer(Answer answer) {
		return this.aRepo.save(answer);
	}
	
	//Get all questions
	public List<Question> getAllQuestions() {
		return this.qRepo.findAll();
	}
	
	//Get question by id.
	public Question getQuestionById(Long id) {
		return this.qRepo.findById(id).orElse(null);
	}

	//Get answer by id.
	public Answer getAnswerById(Long id) {
		return this.aRepo.findById(id).orElse(null);
	}
	
	//Delete question by id.
	public void deleteQuestionById(Long id) {
		this.qRepo.deleteById(id);
	}
	
	//Delete answer by id.
	public void deleteAnswerById(Long id) {
		this.aRepo.deleteById(id);
	}
	
	
	
	
	
	
	
}
