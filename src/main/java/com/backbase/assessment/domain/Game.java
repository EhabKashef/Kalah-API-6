package com.backbase.assessment.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import com.backbase.assessment.domain.enums.Player;
import com.backbase.assessment.domain.enums.Status;

import static com.backbase.assessment.configuration.Settings.*;

/**
 * Created by Ehab ElKashef
 */
@Entity
@Table(name = "games")
public class Game {
	@Id
	@GeneratedValue
	private int id;
	@ElementCollection
	@MapKeyColumn(name = "pitId")
	@Column(name = "value")
	private Map<Integer, Integer> board;
	@Enumerated(value = EnumType.STRING)
	private Status status;
	@Enumerated(value = EnumType.STRING)
	private Player player;

	public Game() {
		initializeBoard();
		status = Status.IN_PROGRESS;
		player = Player.FIRST_PLAYER;
	}

	private void initializeBoard() {
		board = new HashMap<>();

		for (int i = FIRST_PIT_INDEX; i <= LAST_PIT_INDEX; i++) {
			int firstKhalIndex = Player.FIRST_PLAYER.getKalahId();
			int secondKhalIndex = Player.SECOND_PLAYER.getKalahId();
			int value = ((i != firstKhalIndex) && (i != secondKhalIndex)) ? INITIAL_STONES_QUANTITY : 0;

			board.put(i, value);
		}
	}

	public Map<Integer, Integer> getBoard() {
		return board;
	}

	public void setBoard(Map<Integer, Integer> board) {
		this.board = board;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}

//~ Formatted by Jindent --- http://www.jindent.com
