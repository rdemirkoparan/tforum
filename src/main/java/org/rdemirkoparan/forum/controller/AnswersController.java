package org.rdemirkoparan.forum.controller;

import static org.rdemirkoparan.forum.util.GlobalConstants.ANSWERS;

import java.util.List;

import org.rdemirkoparan.forum.model.Answer;
import org.rdemirkoparan.forum.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author recepd
 *
 */
@Controller
public class AnswersController {

	private final AnswerService answerService;

    @Autowired
	public AnswersController (AnswerService answerService) {
		this.answerService = answerService;
    }

	/**
	 * Displays users answers
	 * 
	 * @param id
	 *            : user id
	 * @param model
	 *            : answer model
	 * @return answer model
	 */
	@GetMapping("answers/{id}")
	public String displayAnswersByUser (@PathVariable String id, Model model) {
		List<Answer> answers = answerService.findByUserIdOrderByCreatedDateDesc (Long.parseLong (id));
		model.addAttribute (ANSWERS, answers);
		return ANSWERS;
	}

	/**
	 * Display only helpful answers
	 * 
	 * @param id
	 *            : user id
	 * @param model
	 *            : answer model
	 * @return answer model
	 */
	@GetMapping("answers/useful/{id}")
	public String displayUsefulAnswersByUser (@PathVariable String id, Model model) {
		List<Answer> answers = answerService.findByUserIdAndUsefulOrderByCreatedDateDesc (Long.parseLong (id), true);
		model.addAttribute (ANSWERS, answers);
		return ANSWERS;
	}
}
