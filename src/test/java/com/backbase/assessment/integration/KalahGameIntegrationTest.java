package com.backbase.assessment.integration;

 
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriTemplate;
import static com.backbase.assessment.TestUtils.prepareBoardForResult;

import com.backbase.assessment.domain.enums.Status;
import com.backbase.assessment.dto.ExceptionDTO;
import com.backbase.assessment.dto.GameDTO;
import com.backbase.assessment.dto.NewGameDTO;
import com.backbase.assessment.repository.GameRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
/**
 * Integration test that simulates games between two players,
 * all moves are taken from the real example.
 *
 * Created by Alexander Gladun on 02/06/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KalahGameIntegrationTest {

    private static final String CREATE_GAME_ENDPOINT = "/games";
    private static final String MAKE_MOVE_ENDPOINT = "/games/{gameId}/pits/";
    private static final int FIRST_GAME_FIRST_PLAYER_STONES = 23;
    private static final int FIRST_GAME_SECOND_PLAYER_STONES = 49;


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository repository;

  


    private final Map<Integer, Integer> firstGameResult = new HashMap<>();

 

    private String url;

    @Before
    public void prepareGame() {
        NewGameDTO game = restTemplate
                .exchange(CREATE_GAME_ENDPOINT, HttpMethod.POST, null, NewGameDTO.class)
                .getBody();
        url = new UriTemplate(MAKE_MOVE_ENDPOINT).expand(game.getId()).toString();
    }

    @Test
    public void playFirstGame() {
        prepareBoardForResult(FIRST_GAME_FIRST_PLAYER_STONES, FIRST_GAME_SECOND_PLAYER_STONES, firstGameResult);
      int  firstGameIndexes[] =
            {1,2,8,1,11,1,13,4,13,12,13,11,1,9,6,2,13,8,1,11,3,13,12,13,10,1,13,9,6,11,2,12,8,5,13,8,6,3,9,6,4,10,5,6};
        Map<Integer, Integer> result = new HashMap<>();
        for (int pit : firstGameIndexes) {
            GameDTO game = restTemplate.exchange(url + pit, HttpMethod.PUT, null, GameDTO.class).getBody();
            result = game.getStatus();
        }
        Assertions.assertThat(result.size()).isEqualTo(14);
        MatcherAssert.assertThat(result, CoreMatchers.is(firstGameResult));

        ResponseEntity<ExceptionDTO> response = restTemplate.exchange(url + 1, HttpMethod.PUT, null, ExceptionDTO.class);
        ExceptionDTO body = response.getBody();
        Assert.assertEquals(Status.SECOND_PLAYER_WIN, body.getGameStatus());
    }

 

    @After
    public void tearDown() {
        repository.deleteAll();
    }
}
