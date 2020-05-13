package dev.codesquad.java.baseball08.service.henry;

import dev.codesquad.java.baseball08.dao.GameDaoHenry;
import dev.codesquad.java.baseball08.dao.TeamDaoHenry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PitchService {
    private Logger logger = LoggerFactory.getLogger(PitchService.class);

    @Autowired
    private GameDaoHenry gameDaoHenry;

    @Autowired
    private TeamDaoHenry teamDaoHenry;

    public void pitch() {

    }
}
