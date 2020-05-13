package dev.codesquad.java.baseball08.service.alex;

import dev.codesquad.java.baseball08.dao.GameDaoAlex;
import dev.codesquad.java.baseball08.dao.TeamDaoAlex;
import dev.codesquad.java.baseball08.entity.Inning;
import dev.codesquad.java.baseball08.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Alex {
    private Logger logger = LoggerFactory.getLogger(Alex.class);

    @Autowired
    private GameDaoAlex gameDaoAlex;

    @Autowired
    private TeamDaoAlex teamDaoAlex;

    // 피치는 수비만 할 수 있다.
    // 내가 수비인지 아닌지를 판별할 수 있는 근거는?
    // teamId를 보낸 사람이 home인지 away인지 판단을 하고, 공격을 했는지 안했는지를 판단하면 알 수 있겠지?
    // 원정팀은 왼쪽에, 홈팀은 오른쪽에 표시된다.
    // 홈팀 ID로 들어왔는데, 원정팀 공격이 -> true 홈팀 공격 -> true면 Error
    // 홈팀 ID로 들어왔는데, 원정팀 공격 -> true 홈팀 공격 -> false면 OK
    // 원정팀 ID로 들어왔는데, 원정팀 공격이 -> true 홈팀 공격 -> false Error
    // 원정팀 ID로 들어왔는데, 원정팀 공격이 -> true 홈팀 공격 -> true OK
    // 회 초 == awayAttack = true // homeAttack = false
    // 회 말 == awayAttack = true // homeAttack = true

    public boolean pitch(Long teamId) {
        Long gameId = gameDaoAlex.getGameIdByTeamId(teamId);
        Inning inning = gameDaoAlex.getPresentInning(gameId);
        String requestTeamName = teamDaoAlex.findTeamNameByTeamId(teamId);

        if (!inning.isTopBottom()) {
            return topInning(inning, requestTeamName, teamId);
        }
        return bottomInning(inning, requestTeamName, teamId);
    }

    private boolean topInning(Inning inning, String requestTeamName, Long teamId) {
        if (requestTeamName.equals(inning.getAwayName())) {
            return false;
        }
        List<String> players = teamDaoAlex.findPlayersNameByTeamId(teamId);
        return true;
    }

    private boolean bottomInning(Inning inning, String requestTeamName, Long teamId) {
        if (requestTeamName.equals(inning.getHomeName())) {
            return false;
        }
        List<String> players = teamDaoAlex.findPlayersNameByTeamId(teamId);
        return true;
    }
}
