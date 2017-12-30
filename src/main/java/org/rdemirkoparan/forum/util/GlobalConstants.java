package org.rdemirkoparan.forum.util;

/**
 * @author recepd
 *
 */
public final class GlobalConstants {
	private GlobalConstants () {
	}

	public static final String RESOURCES = "/resources/**";
	public static final String HOME = "/home";
	public static final String ROOT = "/";
	public static final String LOGOUT = "/logout";
	public static final String SIGNUP = "/signup";
	public static final String LOGIN = "/login";

	public static final int BEGINNING_LENGTH = 30;
	public static final String BEGINNING_SUFFIX = "...";

	public static final String USERNAME = "username";
	public static final String PSWD = "password";

	public static final String ANSWERS = "answers";

	public static final String TOPICS = "topics";

	public static final String HOMENOTSIGNEDIN = "homeNotSignedIn";
	public static final String HOMESIGNEDIN = "homeSignedIn";

	public static final String PSWD_MUST_BE_BETWEEN_4_AND_16_CHARACTERS = "Password must be between 4 and 16 characters!";
	public static final String PSWD_CANNOT_BE_BLANK = "Password cannot be blank.";
	public static final String USERNAME_ALREADY_TAKEN_BY_SOMEONE = "Username already taken by someone!";
	public static final String USERNAME_MUST_BE_BETWEEN_4_AND_32_CHARACTERS = "Username must be between 4 and 32 characters!";
	public static final String USERNAME_CANNOT_BE_BLANK = "Username cannot be blank.";
}
