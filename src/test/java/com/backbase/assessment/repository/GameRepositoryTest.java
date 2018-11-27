package com.backbase.assessment.repository;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.backbase.assessment.domain.Game;
import com.backbase.assessment.domain.enums.Player;
import com.backbase.assessment.domain.enums.Status;

/**
 * Created by Ehab ElKashef
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {
	@Autowired
	private GameRepository repository;

	@Test
	public void testSaveGame() {
		Game game = new Game();
		Game created = repository.save(game);

		assertNotNull(created);
		assertEquals(1, created.getId());
		assertEquals(Status.IN_PROGRESS, created.getStatus());
		assertEquals(Player.FIRST_PLAYER, created.getPlayer());
	}
}
