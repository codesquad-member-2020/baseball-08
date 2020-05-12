package dev.codesquad.java.baseball08.dao.henry;

import dev.codesquad.java.baseball08.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.henry.GameListResponse;
import dev.codesquad.java.baseball08.dto.henry.PlayerLogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class GameDao2 {
    private Logger logger = LoggerFactory.getLogger(GameDao2.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDao2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GameListResponse> findAllGame() {
        String sql = "SELECT g.id AS game_id, GROUP_CONCAT(t.name) AS team_name, GROUP_CONCAT(COALESCE(t.user_id, 'none')) AS team_user" +
                " FROM game g" +
                " LEFT JOIN team t" +
                " ON g.id = t.game" +
                " GROUP BY g.id";

        RowMapper<GameListResponse> responseRowMapper = (rs, rowNum) -> {
            return GameListResponse.builder()
                    .game(rs.getInt("game_id"))
                    .away(rs.getString("team_name").split(",")[0])
                    .home(rs.getString("team_name").split(",")[1])
                    .awayUser(rs.getString("team_user").split(",")[0])
                    .homeUser(rs.getString("team_user").split(",")[1])
                    .build();
        };
        return jdbcTemplate.query(sql, new Object[] {}, responseRowMapper);
    }

    public AvailabilityResponse IsGameAvailable(Long id) {
        String sql = "SELECT on_game FROM game g WHERE g.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs, rowNum) -> new AvailabilityResponse(rs.getBoolean("on_game")));
    }

    public Optional<PlayerInfoDto> findTeamById(Long id) {
        String sql = "SELECT t.name, t.user_id," +
                " GROUP_CONCAT(p.name) AS group_name, GROUP_CONCAT(p.at_bat) AS group_at_bat," +
                " GROUP_CONCAT(p.hit) AS group_hit, GROUP_CONCAT(p.out_count) AS group_out," +
                " GROUP_CONCAT(p.average) AS group_average," +
                " SUM(p.at_bat) AS total_at_bat, SUM(p.hit) AS total_hit, SUM(p.out_count) AS total_out" +
                " FROM team t" +
                " INNER JOIN player p" +
                " ON t.id = p.team" +
                " WHERE t.id = ?";

        RowMapper<PlayerInfoDto> responsePlayersDtoRowMapper = (rs, rowNum) -> {
            return PlayerInfoDto.builder()
                    .team(rs.getString("name"))
                    .user(rs.getString("user_id"))
                    .names(Arrays.asList(rs.getString("group_name").split(",")))
                    .atBats(Arrays.asList(rs.getString("group_at_bat").split(",")))
                    .hits(Arrays.asList(rs.getString("group_hit").split(",")))
                    .outs(Arrays.asList(rs.getString("group_out").split(",")))
                    .averages(Arrays.asList(rs.getString("group_average").split(",")))
                    .totalAtBat(rs.getInt("total_at_bat"))
                    .totalHit(rs.getInt("total_hit"))
                    .totalOut(rs.getInt("total_out"))
                    .build();
        };
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[] {id}, responsePlayersDtoRowMapper));
    }

    public void save(Integer homeScore, Integer awayScore) {
        String sql = "INSERT INTO game (home_total_score, away_total_score) VALUES (?, ?)";
        this.jdbcTemplate.update(sql, new Object[] {homeScore, awayScore});
    }
}
