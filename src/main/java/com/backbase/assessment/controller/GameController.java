package com.backbase.assessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.backbase.assessment.dto.GameDTO;
import com.backbase.assessment.dto.NewGameDTO;
import com.backbase.assessment.service.GameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Rest endpoints for Kalah.
 *
 * Created by Ehab ElKashef
 */
@RestController
@RequestMapping("/games")
@Api(value = "Kalah", description = "Game endpoints ", tags = ("kalah"))
public class GameController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final GameService service;

	public GameController(GameService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create the game", notes = "Creates new Kalah game")
	public NewGameDTO createNewGame() {
		logger.info("create New Game");

		return service.createNewGame();
	}

	@PutMapping("/{gameId}/pits/{pitId}")
	@ApiOperation(value = "Make a move")
	public GameDTO makeMove(@PathVariable int gameId, @PathVariable int pitId) {
		logger.info("make Move");

		return service.makeMove(gameId, pitId);
	}
}

 