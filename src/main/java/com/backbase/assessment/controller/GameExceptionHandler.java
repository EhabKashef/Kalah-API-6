package com.backbase.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backbase.assessment.dto.ExceptionDTO;
import com.backbase.assessment.exception.GameNotFoundException;
import com.backbase.assessment.exception.GameTerminatedException;
import com.backbase.assessment.exception.IllegalPitNumberException;

/**
 * Converts exceptions to Http statuses. {@link HttpStatus} {@link ExceptionDTO}
 * represents error.
 *
 * Created by Ehab ElKashef
 */
@RestControllerAdvice
public class GameExceptionHandler {
	@ExceptionHandler(IllegalPitNumberException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO handleBadRequest(Exception ex) {
		return new ExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GameTerminatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ExceptionDTO handleConflict(GameTerminatedException ex) {
		return new ExceptionDTO(ex.getMessage(), HttpStatus.CONFLICT, ex.getStatus());
	}

	@ExceptionHandler(GameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDTO handleNotFound(Exception ex) {
		return new ExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}

//~ Formatted by Jindent --- http://www.jindent.com
