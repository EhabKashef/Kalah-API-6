package com.backbase.assessment.exception;

/**
 * Thrown to indicate that provided pit number is invalid.
 *
 * Created by Ehab ElKashef
 */
public class IllegalPitNumberException extends RuntimeException {
	public IllegalPitNumberException(String message) {
		super(message);
	}
}
