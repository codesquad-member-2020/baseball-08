package dev.codesquad.java.baseball08.service.henry;

import dev.codesquad.java.baseball08.dao.GameDaoHenry;
import dev.codesquad.java.baseball08.dao.TeamDaoHenry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.codesquad.java.baseball08.common.CommonStatics.*;

import java.util.Random;

@Service
public class PitchService {
    private Logger logger = LoggerFactory.getLogger(PitchService.class);

    @Autowired
    private GameDaoHenry gameDaoHenry;

    @Autowired
    private TeamDaoHenry teamDaoHenry;

    public void pitch() {
        String pitchResult = getPitchResult();
        logger.info(">>>> 결과는? {}", pitchResult);


    }

    private String getPitchResult() {
        int random = new Random().nextInt(RANDOM_BOUND) + 1;
        String result = "";

        if (random <= 100) {
            result = HIT;
        }
        if (random <= 75 && random > 50) {
            result = STRIKE;
        }
        if (random <= 50 && random > 25) {
            result = BALL;
        }
        if (random <= 25) {
            result = OUT;
        }
        return result;
    }

    private void test() {

    }

}
