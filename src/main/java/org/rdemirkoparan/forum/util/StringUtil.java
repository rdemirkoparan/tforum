package org.rdemirkoparan.forum.util;

import static org.rdemirkoparan.forum.util.GlobalConstants.BEGINNING_LENGTH;
import static org.rdemirkoparan.forum.util.GlobalConstants.BEGINNING_SUFFIX;

/**
 * @author recepd
 *
 */
public class StringUtil {

	private StringUtil () {
	}

	public static String displayBeginning (String content) {
		return content.length () < BEGINNING_LENGTH ? content : content.substring (0, BEGINNING_LENGTH).concat (BEGINNING_SUFFIX);
	}
}
