package com.backbase.assessment.configuration;

/**
 * Utility class which holds basic game parameters.
 *
 * Created by Ehab ElKashef
 */
public class Settings {
	public static final int INITIAL_STONES_QUANTITY = 6;
	public static final int FIRST_PIT_INDEX = 1;
	public static final int LAST_PIT_INDEX = 14;

	private Settings() {
		throw new AssertionError("Current class can not be instantiated");
	}
}

//~ Formatted by Jindent --- http://www.jindent.com
