package dev.codesquad.java.baseball08.dao;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import dev.codesquad.java.baseball08.dto.PlayerInfoDto;
import dev.codesquad.java.baseball08.dto.PlayersDto;
import dev.codesquad.java.baseball08.dto.henry.PlayerLogDto;
import dev.codesquad.java.baseball08.dto.henry.TeamScoreResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TeamDao2 {
    private Logger logger = LoggerFactory.getLogger(TeamDao2.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamDao2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<List<PlayersDto>> findPlayerInfoById(Long id) {
        String sql = "SELECT p.name, p.at_bat, p.hit, p.out_count, p.average FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";

        RowMapper<PlayersDto> playersDtoRowMapper = (rs, rowNum) -> {
            return PlayersDto.builder()
                    .name(rs.getString("name"))
                    .atBat(rs.getInt("at_bat"))
                    .hit(rs.getInt("hit"))
                    .out(rs.getInt("out_count"))
                    .average(rs.getDouble("average"))
                    .build();
        };
        return Optional.ofNullable(jdbcTemplate.query(sql, new Object[] {id}, playersDtoRowMapper));
    }

    public Optional<PlayerInfoDto> findTeamById(Long id) {
        String sql = "SELECT t.name, t.user_id," +
                " GROUP_CONCAT(p.name ORDER BY p.line_up) AS group_name, GROUP_CONCAT(p.at_bat ORDER BY p.line_up) AS group_at_bat," +
                " GROUP_CONCAT(p.hit ORDER BY p.line_up) AS group_hit, GROUP_CONCAT(p.out_count ORDER BY p.line_up) AS group_out," +
                " GROUP_CONCAT(p.average ORDER BY p.line_up) AS group_average," +
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

    public String findUserById(Long id) {
        String sql = "SELECT user_id FROM team t WHERE t.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs, rowNum) -> rs.getString("user_id"));
    }

    // 팀 아이디로 해당 팀의 게임 점수 보기
    public TeamScoreResponse findHomeTeamScoreById(Long id) {
        String sql = "SELECT GROUP_CONCAT(DISTINCT t.name) AS team_name, GROUP_CONCAT(i.home_score) AS team_inning_score," +
                " GROUP_CONCAT(DISTINCT t.user_id) AS team_user, GROUP_CONCAT(DISTINCT g.home_total_score) AS total_home_score" +
                " FROM game g INNER JOIN inning i" +
                " ON g.id = i.game INNER JOIN team t" +
                " ON g.id = t.game" +
                " WHERE t.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {id}, ((rs, rowNum) ->
                TeamScoreResponse.builder()
                        .team(rs.getString("team_name"))
                        .score(Arrays.asList(rs.getString("team_inning_score").split(","))
                                .stream()
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()))
                        .totalScore(rs.getInt("total_home_score"))
                        .user(rs.getString("team_user"))
                        .build())
        );
    }

    public List<PlayerLogDto> findHistoriesById(Long id) {
        String sql = "SELECT GROUP_CONCAT(DISTINCT h.name) AS hitter_name," +
                " GROUP_CONCAT(DISTINCT h.line_up) AS hitter_line_up, GROUP_CONCAT(l.hit_log) AS hit_logs" +
                " FROM team t" +
                " INNER JOIN history h ON h.team = t.id" +
                " INNER JOIN log l ON h.id = l.history" +
                " WHERE t.id = ? GROUP BY h.id";

        return jdbcTemplate.query(sql, new Object[] {id}, (rs, rowNum) ->
                PlayerLogDto.builder()
                        .name(rs.getString("hitter_name"))
                        .lineUp(Integer.parseInt(rs.getString("hitter_line_up")))
                        .hitLog(Arrays.asList(rs.getString("hit_logs").split(",")))
                        .build()
        );
    }
}


