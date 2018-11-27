package com.backbase.assessment.service;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import org.springframework.util.ReflectionUtils;

import static org.junit.Assert.*;

import com.backbase.assessment.domain.Game;
import com.backbase.assessment.domain.enums.Player;
import com.backbase.assessment.domain.enums.Status;

import static com.backbase.assessment.TestUtils.fillBoard;
import static com.backbase.assessment.TestUtils.prepareBoardForResult;

/**
 * Created by Ehab ElKashef
 */
public class GameLogicTest {
	private final GameLogic gameLogic = new GameLogic();
	private Class<? extends GameLogic> clazz = gameLogic.getClass();
	private Game game;
	private Map<Integer, Integer> board;

	@Before
	public void init() {
		game = new Game();
		board = game.getBoard();
	}

	private void openMethodForTest(Method method) {
		ReflectionUtils.makeAccessible(method);
	}

	@Test
	public void testAddAllRemainedStonesToKalah() throws Exception {
		Method addAllRemainedStonesToKalah = clazz.getDeclaredMethod("addAllRemainedStonesToKalah", Player.class,
				Map.class);

		openMethodForTest(addAllRemainedStonesToKalah);
		fillBoard(0, 0, 3, board);
		addAllRemainedStonesToKalah.invoke(gameLogic, Player.FIRST_PLAYER, board);

		Integer amount = board.get(Player.FIRST_PLAYER.getKalahId());

		assertEquals(18, amount.intValue());
	}

	@Test
	public void testAddStonesToPit() throws Exception {
		Method addStonesToPit = clazz.getDeclaredMethod("addStonesToPit", int.class, Map.class, int.class);

		openMethodForTest(addStonesToPit);
		addStonesToPit.invoke(gameLogic, 1, board, 8);
		assertEquals(14, board.get(1).intValue());
	}

	@Test
	public void testCheckLastPit() throws Exception {
		Method checkLastPit = clazz.getDeclaredMethod("checkLastPit", int.class, Game.class);

		openMethodForTest(checkLastPit);
		board.replace(3, 1);
		checkLastPit.invoke(gameLogic, 3, game);

		int pit = board.get(3);
		int oppositePit = board.get(11);
		int kalahAmount = board.get(Player.FIRST_PLAYER.getKalahId());

		assertEquals(0, pit);
		assertEquals(0, oppositePit);
		assertEquals(7, kalahAmount);
	}

	@Test
	public void testClearPit() throws Exception {
		Method clearPit = clazz.getDeclaredMethod("clearPit", int.class, Map.class);

		openMethodForTest(clearPit);
		clearPit.invoke(gameLogic, 3, board);
		assertEquals(0, board.get(3).intValue());
	}

	@Test
	public void testFindTheWinner() throws Exception {
		Method findTheWinner = clazz.getDeclaredMethod("findTheWinner", Game.class);

		openMethodForTest(findTheWinner);
		prepareBoardForResult(23, 49, board);

		Status firstWinner = (Status) findTheWinner.invoke(gameLogic, game);

		assertEquals(Status.SECOND_PLAYER_WIN, firstWinner);
		prepareBoardForResult(49, 23, board);

		Status secondWinner = (Status) findTheWinner.invoke(gameLogic, game);

		assertEquals(Status.FIRST_PLAYER_WIN, secondWinner);
		prepareBoardForResult(36, 36, board);

		Status draw = (Status) findTheWinner.invoke(gameLogic, game);

		assertEquals(Status.DRAW, draw);
	}

	@Test
	public void testGameIsTerminated() throws Exception {
		Method gameIsTerminated = clazz.getDeclaredMethod("gameIsTerminated", Game.class);

		openMethodForTest(gameIsTerminated);

		boolean falseResult = (boolean) gameIsTerminated.invoke(gameLogic, game);

		assertFalse(falseResult);
		prepareBoardForResult(36, 36, board);

		boolean trueResult = (boolean) gameIsTerminated.invoke(gameLogic, game);

		assertTrue(trueResult);
	}

	@Test
	public void testGetOppositePit() throws Exception {
		Method getOppositePit = clazz.getDeclaredMethod("getOppositePit", int.class);

		openMethodForTest(getOppositePit);

		int opposite = (int) getOppositePit.invoke(gameLogic, 8);

		assertEquals(6, opposite);
	}

	@Test
	public void testIsUserPit() throws Exception {
		Method isUserPit = clazz.getDeclaredMethod("isUserPit", int.class, Player.class);

		openMethodForTest(isUserPit);

		for (Integer pit : Player.FIRST_PLAYER.getPits()) {
			boolean result = (boolean) isUserPit.invoke(gameLogic, pit, Player.FIRST_PLAYER);

			assertTrue(result);
		}

		for (Integer pit : Player.SECOND_PLAYER.getPits()) {
			boolean result = (boolean) isUserPit.invoke(gameLogic, pit, Player.SECOND_PLAYER);

			assertTrue(result);
		}

		boolean result = (boolean) isUserPit.invoke(gameLogic, 3, Player.SECOND_PLAYER);

		assertFalse(result);
	}

	@Test
	public void testLastPitWasOwnEmptyPit() throws Exception {
		Method lastPitWasOwnEmptyPit = clazz.getDeclaredMethod("lastPitWasOwnEmptyPit", int.class, Game.class);

		openMethodForTest(lastPitWasOwnEmptyPit);
		board.replace(3, 1);

		boolean trueResult = (boolean) lastPitWasOwnEmptyPit.invoke(gameLogic, 3, game);

		assertTrue(trueResult);
		board.replace(8, 1);

		boolean falseResult = (boolean) lastPitWasOwnEmptyPit.invoke(gameLogic, 8, game);

		assertFalse(falseResult);
	}

	@Test
	public void testMakeMove() {
		int pitId = 3;
		int pitAmount = board.get(pitId);
		Map<Integer, Integer> beforeMove = new HashMap<>(board);

		gameLogic.makeMove(game, pitId);
		IntStream.range(pitId + 1, pitAmount + 1).forEach(pit -> {
			int amount = board.get(pit);

			assertEquals(amount, beforeMove.get(pit) + 1);
		});
	}

	@Test
	public void testPlayerHasAnotherTurn() throws Exception {
		Method playerHasAnotherTurn = clazz.getDeclaredMethod("playerHasAnotherTurn", int.class, Player.class);

		openMethodForTest(playerHasAnotherTurn);

		boolean first = (boolean) playerHasAnotherTurn.invoke(gameLogic, 7, Player.FIRST_PLAYER);
		boolean second = (boolean) playerHasAnotherTurn.invoke(gameLogic, 14, Player.SECOND_PLAYER);
		boolean invalid = (boolean) playerHasAnotherTurn.invoke(gameLogic, 12, Player.SECOND_PLAYER);

		assertTrue(first);
		assertTrue(second);
		assertFalse(invalid);
	}
}
