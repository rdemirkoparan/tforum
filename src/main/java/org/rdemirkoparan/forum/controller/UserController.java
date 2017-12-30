package org.rdemirkoparan.forum.controller;

import static org.rdemirkoparan.forum.util.GlobalConstants.HOME;
import static org.rdemirkoparan.forum.util.GlobalConstants.HOMENOTSIGNEDIN;
import static org.rdemirkoparan.forum.util.GlobalConstants.HOMESIGNEDIN;
import static org.rdemirkoparan.forum.util.GlobalConstants.LOGIN;
import static org.rdemirkoparan.forum.util.GlobalConstants.LOGOUT;
import static org.rdemirkoparan.forum.util.GlobalConstants.ROOT;
import static org.rdemirkoparan.forum.util.GlobalConstants.SIGNUP;

import javax.servlet.http.HttpServletRequest;

import org.rdemirkoparan.forum.helper.TopicHelper;
import org.rdemirkoparan.forum.model.Topic;
import org.rdemirkoparan.forum.model.User;
import org.rdemirkoparan.forum.service.AnswerService;
import org.rdemirkoparan.forum.service.TopicService;
import org.rdemirkoparan.forum.service.UserService;
import org.rdemirkoparan.forum.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author recepd
 *
 */
@Controller
public class UserController {

	@Autowired
    private UserService userService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private AnswerService answerService;

    @Autowired
    private UserValidator userValidator;

	/**
	 * Empty register form sets user object to userform attribute
	 * 
	 * @param model
	 *            : signup model
	 * @return signup model
	 */
	@GetMapping(SIGNUP)
	public String signup (Model model) {
        model.addAttribute("userForm", new User());
		return "signup";
    }

	/**
	 * New user registration request.
	 * 
	 * @param userForm
	 *            : user object
	 * @param bindingResult
	 *            : for validator
	 * @param model
	 *            : signup model
	 * @return signup on failure or login on success
	 */
	@PostMapping(SIGNUP)
	public String signup (@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
			model.addAttribute ("error", bindingResult.getAllErrors ());
			return "signup";
        }

        userService.save(userForm);

		return "redirect:/login";
    }

	/**
	 * Login and logout handler
	 * 
	 * @param model
	 *            : login model
	 * @param error
	 *            : login error if any
	 * @return login model
	 */
	@RequestMapping(value = { LOGIN, LOGOUT })
	public String login (Model model, String error) {
		if (error != null) {
			model.addAttribute ("error", "Your username and password is invalid.");
		}
		return "login";
	}

	/**
	 * Check user authentication and return user's home page or landing page
	 * 
	 * @param model
	 *            : root/home model
	 * @return signed or notsigned home model
	 */
	@RequestMapping(value = { ROOT, HOME }, method = RequestMethod.GET)
	public String home (Model model) {
		return prepareView (model);
	}

	/**
	 * Post new topic
	 * 
	 * @param title
	 *            : topic title
	 * @param content
	 *            : topic content
	 * @param user_id
	 *            : user id
	 * @param request
	 *            : http request
	 * @return signed home model
	 */
	@RequestMapping(value = { ROOT, HOME }, method = RequestMethod.POST)
	public View postTopic (@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("user_id") String user_id,
			HttpServletRequest request) {
		Topic topic = new TopicHelper ()
				.start ()
				.addTitle (title)
				.addContent (content)
				.addUser (userService.findById (Long.parseLong (user_id)))
				.end ();

		topicService.save (topic);
		return new RedirectView (request.getContextPath () + HOME);
	}

	/**
	 * Displays given topic
	 * 
	 * @param id
	 *            : user id
	 * @param model
	 *            : home model
	 * @return signed or notsigned home model
	 */
	@GetMapping("home/{id}")
	public String displayTopic (@PathVariable String id, Model model) {
		Long loggedUserId = findUserId ();
		if (null != loggedUserId) {
			User user = userService.findById (Long.valueOf (id));
			prepareViewAttributes (model, user, loggedUserId);
			return HOMESIGNEDIN;
		} else {
			return HOMENOTSIGNEDIN;
		}
	}

	/**
	 * home view preparation helper
	 * 
	 * @param model
	 *            : home model (using to set attributes)
	 * @return signed or notsigned home model
	 */
	private String prepareView (Model model) {
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		if (null != principal) {
			if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername ();
				User user = userService.findByUsername (username);
				if (null == user) {
					return HOMENOTSIGNEDIN;
				}
				prepareViewAttributes (model, user, user.getId ());
			} else {
				return HOMENOTSIGNEDIN;
			}
		}
		return HOMESIGNEDIN;
	}

	/**
	 * Home attribute setters to use with thymeleaf
	 * 
	 * @param model
	 *            : home model
	 * @param user
	 *            : owner user
	 * @param userId
	 *            : logged user id
	 */
	private void prepareViewAttributes (Model model, User user, Long userId) {
		Long numberOfTopics = topicService.countByUserId (user.getId ());
		Long numberOfAnswers = answerService.countByUserId (user.getId ());
		Long numberOfHelped = answerService.countByUserIdAndUseful (user.getId (), true);

		// reputation calculate by; number of topics + 2 * number of answers + 5 * number of useful answers 
		Long reputation = numberOfTopics + (2 * numberOfAnswers) + (5 * numberOfHelped);

		model.addAttribute ("user", user);
		model.addAttribute ("idUser", userId);
		model.addAttribute ("reputation", reputation);
		model.addAttribute ("numberOfTopics", numberOfTopics);
		model.addAttribute ("numberOfAnswers", numberOfAnswers);
		model.addAttribute ("numberOfHelped", numberOfHelped);
	}

	/**
	 * Find logged user id
	 * 
	 * @return logged in user id or null
	 */
	private Long findUserId () {
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		if (null != principal && principal instanceof UserDetails) {
			User user = userService.findByUsername (((UserDetails) principal).getUsername ());
			return null != user ? user.getId () : null;
		}
		return null;
	}

}
