package com.backbase.assessment.dto;

import org.springframework.http.HttpStatus;

import com.backbase.assessment.domain.enums.Status;

/**
 * Created by Ehab ElKashef
 */
public class ExceptionDTO {
	private String message;
	private HttpStatus httpStatus;
	private Status gameStatus;

	public ExceptionDTO() {
	}

	public ExceptionDTO(String message, HttpStatus status) {
		this(message, status, null);
	}

	public ExceptionDTO(String message, HttpStatus httpStatus, Status gameStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.gameStatus = gameStatus;
	}

	public Status getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(Status gameStatus) {
		this.gameStatus = gameStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return httpStatus;
	}
}
