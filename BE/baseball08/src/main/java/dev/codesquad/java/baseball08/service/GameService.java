package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.dao.GameDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private GameDao gameDao;

    public void isGamePossible() {

    }
}
