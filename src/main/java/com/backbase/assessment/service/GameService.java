package com.backbase.assessment.service;

import com.backbase.assessment.dto.GameDTO;
import com.backbase.assessment.dto.NewGameDTO;

/**
 * Service layer, represents business logic for API endpoints.
 *
 * Created by Ehab ElKashef
 */
public interface GameService {

	/**
	 * Creates new game.
	 *
	 * @return new game
	 */
	NewGameDTO createNewGame();

	/**
	 * Makes a move of current associated player.
	 *
	 * @param gameId unique identifier of a game
	 * @param pitId  id of the pit selected to make a move
	 * @return modified game
	 * @throws com.backbase.assessment.exception.GameTerminatedException if the game
	 *         has been already terminated.
	 *
	 * @throws com.backbase.assessment.exception.GameNotFoundException if provided
	 *         game identifier is not associated with any game in database.
	 *
	 */
	GameDTO makeMove(int gameId, int pitId);
}
