package com.backbase.assessment.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ehab ElKashef
 */
public enum Player {
	FIRST_PLAYER(7, Arrays.asList(1, 2, 3, 4, 5, 6)), SECOND_PLAYER(14, Arrays.asList(8, 9, 10, 11, 12, 13));

	private final int kalahId;
	private final List<Integer> pits;

	Player(int kalahId, List<Integer> pits) {
		this.kalahId = kalahId;
		this.pits = Collections.unmodifiableList(pits);
	}

	public int getKalahId() {
		return kalahId;
	}

	public Player getOppositePlayer() {
		return (this == FIRST_PLAYER) ? SECOND_PLAYER : FIRST_PLAYER;
	}

	public List<Integer> getPits() {
		return pits;
	}
}

//~ Formatted by Jindent --- http://www.jindent.com
