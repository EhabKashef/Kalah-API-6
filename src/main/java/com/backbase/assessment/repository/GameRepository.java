package com.backbase.assessment.repository;

import org.springframework.data.repository.CrudRepository;

import com.backbase.assessment.domain.Game;

/**
 * Data access layer.
 *
 * Created by Ehab ElKashef
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
}
