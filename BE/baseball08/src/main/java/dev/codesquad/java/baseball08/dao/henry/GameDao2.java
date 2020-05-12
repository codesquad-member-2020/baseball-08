package dev.codesquad.java.baseball08.dao.henry;

import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.henry.GameListResponse;
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
}
