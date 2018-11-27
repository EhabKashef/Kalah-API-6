package com.backbase.assessment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backbase.assessment.domain.Game;
import com.backbase.assessment.domain.enums.Status;
import com.backbase.assessment.dto.GameDTO;
import com.backbase.assessment.dto.NewGameDTO;
import com.backbase.assessment.exception.GameNotFoundException;
import com.backbase.assessment.exception.GameTerminatedException;
import com.backbase.assessment.mapper.GameMapper;
import com.backbase.assessment.repository.GameRepository;

/**
 * Basic {@link GameService} implementation.
 *
 * Created by Ehab ElKashef
 */
@Service
public class GameServiceImpl implements GameService {
	private final GameRepository repository;
	private final GameMapper mapper;
	private final GameLogic gameLogic;

	public GameServiceImpl(GameRepository repository, GameMapper mapper, GameLogic gameLogic) {
		this.repository = repository;
		this.mapper = mapper;
		this.gameLogic = gameLogic;
	}

	@Override
	@Transactional
	public NewGameDTO createNewGame() {
		Game created = repository.save(new Game());

		return mapper.toNewDTO(created);
	}

	@Override
	@Transactional
	public GameDTO makeMove(int gameId, int pitId) {
		Game game = repository.findById(gameId)
				.orElseThrow(() -> new GameNotFoundException("Game with id: " + gameId + " not found."));

		// checkGameStatus
		Status status = game.getStatus();

		if (game.getStatus() != Status.IN_PROGRESS) {
			throw new GameTerminatedException("Game has been already terminated with status:" + status, status);
		}

		// check game logic
		gameLogic.makeMove(game, pitId);

		Game afterMove = repository.save(game);

		return mapper.toDTO(afterMove);
	}
}
