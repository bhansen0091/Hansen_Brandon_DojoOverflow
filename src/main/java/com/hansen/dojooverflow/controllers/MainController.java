package com.hansen.dojooverflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hansen.dojooverflow.models.Answer;
import com.hansen.dojooverflow.models.Question;
import com.hansen.dojooverflow.services.AppService;

@Controller
public class MainController {
	
	private final AppService appService;
	
	public MainController(AppService appService) {
		super();
		this.appService = appService;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Question> questions = appService.getAllQuestions();
		model.addAttribute("questions", questions);		
		return "index.jsp";
	}
	
	//Make Question Form display.
	@GetMapping("/questions/new")
	public String newQuestion(@ModelAttribute("question") Question question) {
		return "newQuestionForm.jsp";
	}
	
	//Add Question to db.
	@PostMapping("/questions/new")
	public String createQuestion(@Valid @ModelAttribute("question") Question question,
			BindingResult result) {
		
		System.out.println(result);
		
		if (result.hasErrors()) {
			return "newQuestionForm.jsp";
		} else {
			appService.createQuestion(question);
			return "redirect:/";
		}
	}
	
	//Show Question / with form for adding answers
	@GetMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, 
			@ModelAttribute("answer") Answer answer,
			Model model) {
		Question question = appService.getQuestionById(id);
		model.addAttribute("question", question);
		return "showQuestion.jsp";
	}
	
	//Add Answer to db
	@PostMapping("/questions/addAnswer")
	public String createAnswer(@Valid @ModelAttribute("answer") Answer answer,
			BindingResult result) {
		if (result.hasErrors()) {
			return "showQuestion.jsp";
		} else {
			Answer newAnswer = appService.createAnswer(answer);
			return "redirect:/questions/" + newAnswer.getQuestion().getId();
		}
	}
	
	//Delete a question
	@GetMapping("/questions/delete/{id}")
	public String deleteQuestion(@PathVariable("id") Long id) {
		appService.deleteQuestionById(id);
		return "redirect:/";
	}
	
	@GetMapping("/answers/delete/{id}")
	public String deleteAnswer(@PathVariable("id") Long id) {
		Answer selectedAnswer = appService.getAnswerById(id);
		String questionId = Long.toString(selectedAnswer.getQuestion().getId());
		appService.deleteAnswerById(id);
		return "redirect:/questions/" + questionId;
	}
	
	
}























