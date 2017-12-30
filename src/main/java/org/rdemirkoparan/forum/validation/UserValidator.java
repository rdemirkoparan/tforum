package org.rdemirkoparan.forum.validation;

import static org.rdemirkoparan.forum.util.GlobalConstants.PSWD;
import static org.rdemirkoparan.forum.util.GlobalConstants.PSWD_CANNOT_BE_BLANK;
import static org.rdemirkoparan.forum.util.GlobalConstants.PSWD_MUST_BE_BETWEEN_4_AND_16_CHARACTERS;
import static org.rdemirkoparan.forum.util.GlobalConstants.USERNAME;
import static org.rdemirkoparan.forum.util.GlobalConstants.USERNAME_ALREADY_TAKEN_BY_SOMEONE;
import static org.rdemirkoparan.forum.util.GlobalConstants.USERNAME_CANNOT_BE_BLANK;
import static org.rdemirkoparan.forum.util.GlobalConstants.USERNAME_MUST_BE_BETWEEN_4_AND_32_CHARACTERS;

import org.rdemirkoparan.forum.model.User;
import org.rdemirkoparan.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validates username and password while new user registeration
 * 
 * @author recepd
 */
@Component
public class UserValidator implements Validator {

	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	/**
	 * Validate input user object and in case of error reject with error
	 */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace (errors, USERNAME, USERNAME_CANNOT_BE_BLANK);

		if (user.getUsername ().length () < 4 || user.getUsername ().length () > 32) {
			errors.rejectValue (USERNAME, USERNAME_MUST_BE_BETWEEN_4_AND_32_CHARACTERS);
        }
        if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue (USERNAME, USERNAME_ALREADY_TAKEN_BY_SOMEONE);
        }

		ValidationUtils.rejectIfEmptyOrWhitespace (errors, PSWD, PSWD_CANNOT_BE_BLANK);

		if (user.getPassword ().length () < 4 || user.getPassword ().length () > 16) {
			errors.rejectValue (PSWD, PSWD_MUST_BE_BETWEEN_4_AND_16_CHARACTERS);
        }
    }
}
