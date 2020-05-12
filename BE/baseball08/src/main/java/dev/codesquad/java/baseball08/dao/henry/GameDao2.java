package dev.codesquad.java.baseball08.dao.henry;

import dev.codesquad.java.baseball08.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.henry.AvailabilityResponse;
import dev.codesquad.java.baseball08.dto.henry.GameListResponse;
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
import java.util.Optional;

@Repository
public class GameDao2 {
    private Logger logger = LoggerFactory.getLogger(GameDao2.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDao2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<GameListResponse> findAllGame(Long id) {
        String sql = "SELECT g.id, t.name, t.user_id FROM game g INNER JOIN team t ON g.id = t.game WHERE g.id = ?";

        RowMapper<GameListResponse> responseRowMapper = (rs, rowNum) -> {
            return GameListResponse.builder()
                    .away(rs.getString(""))
                    .build();
        };
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[] {id}, responseRowMapper));
    }

    public AvailabilityResponse IsGameAvailable(Long id) {
        String sql = "SELECT on_game FROM game g WHERE g.id = ?";

        RowMapper<AvailabilityResponse> responseRowMapper = (rs, rowNum) -> {
            return AvailabilityResponse.builder()
                    .available(rs.getBoolean("on_game"))
                    .build();
        };
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, responseRowMapper);
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
}
