package dev.codesquad.java.baseball08.dao;

import dev.codesquad.java.baseball08.dto.dto.PitcherDto;
import dev.codesquad.java.baseball08.dto.dto.PlayballDto;
import dev.codesquad.java.baseball08.dto.dto.PlayersDto;
import dev.codesquad.java.baseball08.entity.Inning;
import dev.codesquad.java.baseball08.entity.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Repository
public class PitchDaoAlex {
    private Logger logger = LoggerFactory.getLogger(PitchDaoAlex.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PitchDaoAlex(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayballDto findDataToPlayGame(Long gameId) {
        String sql = "SELECT g.id AS game_id , g.home_total_score AS ht_score , g.away_total_score AS at_score , " +
                "i.id AS inning_id , i.home_score as h_score , i.away_score as a_score , i.strike_count , i.ball_count , i.out_count , i.base_count , i.top_bottom , i.home_name , i.away_name , " +
                "from game g left join inning i on g.id = i.game where i.game_key = (SELECT MAX(game_key) FROM inning) AND g.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{gameId},
                ((rs, rowNum) -> PlayballDto.builder()
                        .gameId(rs.getLong("game_id")).homeTotalScore(rs.getInt("ht_score")).awayTotalScore(rs.getInt("at_score"))
                        .inningId(rs.getLong("inning_id")).homeScore(rs.getInt("h_score")).awayScore(rs.getInt("a_score"))
                        .strikeCount(rs.getInt("strike_count")).ballCount(rs.getInt("ball_count")).outCount(rs.getInt("out_count")).baseCount(rs.getInt("base_count"))
                        .topBottom(rs.getBoolean("top_bottom")).homeName(rs.getString("home_name")).awayName(rs.getString("away_name")).build()));
    }

    public PitcherDto findPitcherByTeamId(Long teamId) {
        String sql = "SELECT t.pitcher,t.pitches FROM team t WHERE t.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{teamId},
                (rs, rowNum) -> PitcherDto.builder().name(rs.getString("pitcher")).pitches(rs.getInt("pitches")).build());
    }

    public void savePitchResult(PlayballDto playballDto) {
        String gameSql = "UPDATE game SET home_total_score = ?, away_total_score = ? WHERE id = ?";
        String inningSql = "UPDATE inning SET home_score = ?, away_score = ?, strike_count = ?, ball_count = ?, out_count = ?, base_count = ?, top_bottom = ? WHERE id = ?";
        jdbcTemplate.update(gameSql, new Object[]{playballDto.getHomeTotalScore(), playballDto.getAwayTotalScore(), playballDto.getGameId()});
        jdbcTemplate.update(inningSql, new Object[]{playballDto.getHomeScore(), playballDto.getAwayScore(), playballDto.getStrikeCount(),
                playballDto.getBallCount(), playballDto.getOutCount(), playballDto.getBaseCount(), playballDto.isTopBottom(), playballDto.getInningId()});
    }

    public void savePlayerResult(PlayersDto playersDto, Long otherTeamId) {
        String sql = "UPDATE player SET at_bat = ?, hit = ?, out_count = ? WHERE name = ? AND team = ?";
        jdbcTemplate.update(sql, new Object[]{playersDto.getAtBat(), playersDto.getHit(), playersDto.getOut(), playersDto.getName(), otherTeamId});
    }

    public void saveTeamInfo(String currentHitter, String lastHitter, int pitches, Long otherTeamId, Long teamId) {
        String sql = "UPDATE team SET current_hitter = ?,last_hitter = ? WHERE id = ?";
        String pitchesSql = "UPDATE team SET pitches = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{currentHitter, lastHitter, otherTeamId});
        jdbcTemplate.update(pitchesSql, new Object[]{pitches,teamId});
    }

}
