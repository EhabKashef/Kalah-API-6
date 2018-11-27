package com.backbase.assessment.exception;

/**
 * Thrown to indicate that provided game identifier is not associated with any
 * record in database.
 *
 * Created by Ehab ElKashef
 */
public class GameNotFoundException extends RuntimeException {
	public GameNotFoundException(String message) {
		super(message);
	}
}
