package org.rdemirkoparan.forum.controller;

import static org.rdemirkoparan.forum.util.GlobalConstants.TOPICS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.rdemirkoparan.forum.helper.AnswerHelper;
import org.rdemirkoparan.forum.model.Answer;
import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.service.AnswerService;
import org.rdemirkoparan.forum.service.TopicService;
import org.rdemirkoparan.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author recepd
 *
 */
@Controller
public class TopicsController {

	private final UserService userService;
	private final TopicService topicService;
	private final AnswerService answerService;

    @Autowired
	public TopicsController (UserService userService, TopicService topicService, AnswerService answerService) {
		this.userService = userService;
		this.topicService = topicService;
		this.answerService = answerService;
    }

	/**
	 * Display given topic details
	 * 
	 * @param id
	 *            : user id
	 * @param model
	 *            : topic model
	 * @return topic model
	 */
	@GetMapping("topic/{id}")
	public String displayTopic (@PathVariable String id, Model model) {
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		String username = ((UserDetails) principal).getUsername ();
		Long idUser = userService.findByUsername (username).getId ();

		Topic topic = topicService.findById (Long.valueOf (id));
		List<Answer> answers = answerService.findByTopicId (Long.valueOf (id));

		model.addAttribute ("topic", topic);
		model.addAttribute ("answers", answers);
		model.addAttribute ("idUser", idUser);
		return "topic";
	}

	/**
	 * Adds new topic
	 * 
	 * @param topic_id
	 *            : topic id
	 * @param action
	 *            : action ( acceptable values : useful and delete
	 * @param answer_id
	 *            : answer id
	 * @param state
	 *            : valid only useful action. determines its value (true|false)
	 * @param request
	 *            : http request
	 * @return
	 */
	@PostMapping("topic/{id}")
	public View updateAnswer (@RequestParam String topic_id, @RequestParam String action, @RequestParam String answer_id,
			@RequestParam(required = false) String state, HttpServletRequest request) {
		if ("useful".equals (action)) {
			answerService.setUseful (!Boolean.valueOf (state), Long.valueOf (answer_id));
		} else if ("delete".equals (action)) {
			answerService.deleteById (Long.valueOf (answer_id));
		}
		return new RedirectView (request.getContextPath () + "/topic/" + topic_id);
	}

	/**
	 * Adds answer to that topic
	 * 
	 * @param content
	 *            : answer content
	 * @param topic_id
	 *            : topic id
	 * @param user_id
	 *            : user id
	 * @param request
	 *            : http request
	 * @return
	 */
	@PostMapping("topic")
	public View addAnswer (@RequestParam("content") String content, @RequestParam("topic_id") String topic_id,
			@RequestParam("user_id") String user_id, HttpServletRequest request) {
		Answer answer = new AnswerHelper ()
				.start ()
				.addContent (content)
				.addUser (userService.findById (Long.parseLong (user_id)))
				.addTopic (topicService.findById (Long.valueOf (topic_id)))
				.end ();

		answerService.save (answer);
		return new RedirectView (request.getContextPath () + "/topic/" + topic_id);
	}

	/**
	 * Display system wide topics
	 * 
	 * @param model
	 *            : topics model
	 * @return topics model
	 */
    @GetMapping(TOPICS)
    public String displayAllTopics(Model model) {
		List<Topic> topics = topicService.findAll (new Sort (Sort.Direction.DESC, "createdDate"));
		String header = "All topics";
        model.addAttribute(TOPICS, topics);
        model.addAttribute("header", header);
		model.addAttribute ("answerService", answerService);
        return TOPICS;
    }

	/**
	 * Display user's topics
	 * 
	 * @param id
	 *            : user id
	 * @param model
	 *            : topics model
	 * @return topics model
	 */
	@GetMapping("topics/user/{id}")
	public String displayTopicsByUser (@PathVariable String id, Model model) {
		List<Topic> topics = topicService.findTopicsByUserIdOrderByCreatedDateDesc (Long.valueOf (id));
		String header = "User's topics";
		model.addAttribute (TOPICS, topics);
		model.addAttribute ("header", header);
		model.addAttribute ("answerService", answerService);
		return TOPICS;
	}
}