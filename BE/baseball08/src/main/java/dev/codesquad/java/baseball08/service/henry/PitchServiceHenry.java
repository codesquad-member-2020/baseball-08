package dev.codesquad.java.baseball08.service.henry;

import dev.codesquad.java.baseball08.dao.GameDaoHenry;
import dev.codesquad.java.baseball08.dao.TeamDaoHenry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dev.codesquad.java.baseball08.common.CommonStatics.*;

import java.util.Random;

@Service
@Transactional
public class PitchServiceHenry {
    private Logger logger = LoggerFactory.getLogger(PitchServiceHenry.class);

    @Autowired
    private GameDaoHenry gameDaoHenry;

    @Autowired
    private TeamDaoHenry teamDaoHenry;

    private int strike = 0;
    private int ball = 0;
    private int out = 0;
    private int hit = 0;
    private int base = 0;
    private int lineUp = 1;

    public void pitch(Long teamId) {
        String pitchResult = getPitchResult();
        addCount(pitchResult);
        logger.info(">>> strike: {}, ball: {}, out: {}, base: {}, hit: {}, lineUp: {}", strike, ball, out, base, hit, lineUp);
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

    private void addCount(String pitchResult) {
        switch (pitchResult) {
            case HIT:
                hitCount();
                break;
            case STRIKE:
                strikeCount();
                break;
            case BALL:
                ballCount();
                break;
            case OUT:
                outCount();
                break;
        }
    }

    private void hitCount() {
        hit++;
        base++;
        strike = 0;
        ball = 0;
        increaseLineUp();
        logger.info(">>> HIT");
    }

    private void strikeCount() {
        strike++;
        if (isThreeStrike(strike)) {
            strike = 0;
            ball = 0;
            out++;
            increaseLineUp();
        }
        logger.info(">>> STRIKE");
    }

    private void ballCount() {
        ball++;
        if (isFourBall(ball)) {
            strike++;
            ball = 0;
            base++;
            hit++;
            increaseLineUp();
            logger.info(">>> 4BALL");
        }
        logger.info(">>> BALL");
    }

    private void outCount() {
        out++;
        strike = 0;
        ball = 0;
        increaseLineUp();
        if (isThreeOut(out)) {
            out = 0;
            base = 0;
            logger.info(">>> 3OUT");
        }
        logger.info(">>> OUT");
    }

    private boolean isThreeStrike(int strike) {
        if (strike == 3) {
            return true;
        }
        return false;
    }

    private boolean isFourBall(int ball) {
        if (ball == 4) {
            return true;
        }
        return false;
    }

    private boolean isThreeOut(int out) {
        if (out == 3) {
            return true;
        }
        return false;
    }

    private void increaseLineUp() {
        lineUp++;
        if (lineUp > 9) {
            lineUp = 1;
        }
    }
}
