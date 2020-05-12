package dev.codesquad.java.baseball08.dao.henry;

import dev.codesquad.java.baseball08.dao.TeamDao2;
import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.henry.GameListResponse;
import dev.codesquad.java.baseball08.dto.henry.GamePlayResponse;
import dev.codesquad.java.baseball08.dto.henry.ScoreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDao2 {
    private Logger logger = LoggerFactory.getLogger(GameDao2.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TeamDao2 teamDao2;

    @Autowired
    public GameDao2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GameListResponse> findAllGame() {
        String sql = "SELECT g.id AS game_id, GROUP_CONCAT(t.name) AS team_name, GROUP_CONCAT(COALESCE(t.user_id, 'none')) AS team_user" +
                " FROM game g" +
                " LEFT JOIN team t ON g.id = t.game" +
                " GROUP BY g.id";

        return jdbcTemplate.query(sql, new Object[] {}, (rs, rowNum) ->
                GameListResponse.builder()
                        .game(rs.getInt("game_id"))
                        .away(rs.getString("team_name").split(",")[0])
                        .home(rs.getString("team_name").split(",")[1])
                        .awayUser(rs.getString("team_user").split(",")[0])
                        .homeUser(rs.getString("team_user").split(",")[1])
                        .build()
        );
    }

    public AvailabilityResponse IsGameAvailable(Long id) {
        String sql = "SELECT on_game FROM game g WHERE g.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs, rowNum) ->
                new AvailabilityResponse(rs.getBoolean("on_game"))
        );
    }

    public GamePlayResponse findGameInfoById(Long id) {
        String sql = "SELECT GROUP_CONCAT(DISTINCT t.name) AS team_name," +
                " GROUP_CONCAT(DISTINCT g.away_total_score) AS away_total_score," +
                " GROUP_CONCAT(DISTINCT g.home_total_score) AS home_total_score," +
                " GROUP_CONCAT(DISTINCT g.current_inning) AS current_inning," +
                " GROUP_CONCAT(DISTINCT g.turn) AS turn," +
                " GROUP_CONCAT(DISTINCT i.strike_count) AS strike_count," +
                " GROUP_CONCAT(DISTINCT i.ball_count) AS ball_count," +
                " GROUP_CONCAT(DISTINCT i.out_count) AS out_count," +
                " GROUP_CONCAT(DISTINCT i.base_count) AS base_count" +
                " FROM game g" +
                " INNER JOIN inning i ON g.id = i.game" +
                " INNER JOIN team t ON g.id = t.game" +
                " WHERE g.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs, rowNum) ->
                GamePlayResponse.builder()
                        .away(rs.getString("team_name").split(",")[0])
                        .home(rs.getString("team_name").split(",")[1])
                        .awayTotalScore(rs.getInt("away_total_score"))
                        .homeTotalScore(rs.getInt("home_total_score"))
                        .inning(rs.getInt("current_inning"))
                        .turn(rs.getString("turn"))
                        .score(ScoreDto.builder()
                                .strike(rs.getInt("strike_count"))
                                .ball(rs.getInt("ball_count"))
                                .out(rs.getInt("out_count"))
                                .base(rs.getInt("base_count"))
                                .build())
                        .build()
        );
    }
}
