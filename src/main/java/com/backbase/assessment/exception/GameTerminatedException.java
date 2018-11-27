package com.backbase.assessment.exception;

import com.backbase.assessment.domain.enums.Status;

/**
 * Thrown to indicate that requested game has been already terminated.
 *
 * Created by Ehab ElKashef
 */
public class GameTerminatedException extends RuntimeException {
	private final Status status;

	public GameTerminatedException(String message, Status status) {
		super(message);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
}
