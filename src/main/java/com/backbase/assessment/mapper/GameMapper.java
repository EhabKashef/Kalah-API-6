package com.backbase.assessment.mapper;

import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import com.backbase.assessment.domain.Game;
import com.backbase.assessment.dto.GameDTO;
import com.backbase.assessment.dto.NewGameDTO;

/**
 * Converts Entity to data transfer object.
 *
 * Created by Ehab ElKashef
 */
@Component
public class GameMapper {
	private String gameUrl;
	private final Environment environment;

	public GameMapper(Environment environment) {
		this.environment = environment;
		this.gameUrl = environment.getProperty("game.url");
	}

	private String generateGameUrl(int gameId) {
		return new UriTemplate(gameUrl).expand(gameId).toString();
	}

	public GameDTO toDTO(Game game) {
		int id = game.getId();
		Map<Integer, Integer> status = game.getBoard();
		String url = generateGameUrl(id);

		return new GameDTO(id, url, status);
	}

	public NewGameDTO toNewDTO(Game game) {
		int id = game.getId();
		String url = generateGameUrl(id);

		return new NewGameDTO(id, url);
	}
}
