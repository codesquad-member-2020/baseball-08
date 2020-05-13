package dev.codesquad.java.baseball08.dao;

import dev.codesquad.java.baseball08.dto.dto.StageDto;
import dev.codesquad.java.baseball08.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GameDaoAlex {
    private static final Logger logger = LoggerFactory.getLogger(GameDaoAlex.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public GameDaoAlex(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<String> getGameUserId(Long gameId) {
        String sql = "SELECT t.user_id FROM team t WHERE t.game = ?";
        return jdbcTemplate.query(sql, new Object[]{gameId}, (rs, rowNum) -> rs.getString("user_id"));
    }

    public List<Long> getGameTeamId(Long gameId) {
        String sql = "SELECT t.id FROM team t WHERE t.game = ?";
        return jdbcTemplate.query(sql, new Object[]{gameId}, (rs, rowNum) -> rs.getLong("id"));
    }

    public List<StageDto> getGameInfo() {
        String sql = "SELECT g.id, GROUP_CONCAT(t.name) AS teams, GROUP_CONCAT(COALESCE(t.user_id,'None')) AS users FROM game g left JOIN team t ON g.id = t.game GROUP BY g.id";
        return jdbcTemplate.query(sql, new Object[]{}, (rs, rowNum) ->
                StageDto.builder()
                        .game(rs.getInt("id"))
                        .away(rs.getString("teams").split(",")[0])
                        .home(rs.getString("teams").split(",")[1])
                        .awayUser(rs.getString("users").split(",")[0])
                        .homeUser(rs.getString("users").split(",")[1])
                        .build());
    }

//    public void saveHistory(History history) {
//        String sql = "INSERT INTO history (name,line_up,hit_log,game,game_key) VALUES(?,?,?,?,?)";
//        String[] param = new String[]{"id"};
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement statement = con.prepareStatement(sql, param);
//            statement.setString(1, history.getName());
//            statement.setInt(2, history.getLineUp());
//            statement.setString(3, history.getHitLog());
//            statement.setLong(4, history.getGame());
//            statement.setInt(5, history.getGameKey());
//            return statement;
//        }, keyHolder);
//    }

    public void saveInning(Inning inning) {
        String sql = "INSERT INTO inning(home_name,away_name,home_score,away_score,strike_count,ball_count,out_count,base_count,game,game_key) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        String[] param = new String[]{"id"};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, param);
            statement.setString(1, inning.getHomeName());
            statement.setString(2, inning.getAwayName());
            statement.setInt(3, inning.getHomeScore());
            statement.setInt(4, inning.getAwayScore());
            statement.setInt(5, inning.getStrikeCount());
            statement.setInt(6, inning.getBallCount());
            statement.setInt(7, inning.getOutCount());
            statement.setInt(8, inning.getBaseCount());
            statement.setLong(9, inning.getGame());
            statement.setInt(10, inning.getGame_key());
            return statement;
        }, keyHolder);
    }

//    public int getHistoryCount(Long game) {
//        String sql = "SELECT COUNT(h.id) AS history_count FROM history h WHERE h.game = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{game}, (rs, rowNum) -> rs.getInt("history_count"));
//    }

    public int getGameKeyForInning(Long game) {
        String sql = "SELECT COUNT(i.id) AS inning_count FROM inning i WHERE i.game = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{game}, (rs, rowNum) -> rs.getInt("inning_count"));
    }

    public List<String> getTeamNamesByGameId(Long game) {
        String sql = "SELECT t.name FROM team t WHERE t.game = ?";
        return jdbcTemplate.query(sql, new Object[]{game}, (rs, rowNum) -> rs.getString("name"));
    }

    public void getGameInning(Long game) {

    }
}
