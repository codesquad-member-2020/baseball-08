package dev.codesquad.java.baseball08.dao;

import dev.codesquad.java.baseball08.dto.PlayersDto;
import dev.codesquad.java.baseball08.dto.TotalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeamDao {
    private static final Logger logger = LoggerFactory.getLogger(TeamDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<PlayersDto> findPlayersByTeamId(Long teamId) {
        String findPlayerSql = "SELECT p.name,p.at_bat,p.hit,p.out,p.average FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";
        return jdbcTemplate.query(findPlayerSql, new Object[]{teamId}, new RowMapper<PlayersDto>() {
            @Override
            public PlayersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlayersDto.Builder()
                        .name(rs.getString("name"))
                        .atBat(rs.getInt("at_bat"))
                        .hit(rs.getInt("hit"))
                        .outCount(rs.getInt("out"))
                        .average(rs.getDouble("average"))
                        .build();
            }
        });
    }

    public TotalDto findTotalRecordByTeamId(Long teamId) {
        String findTeamRecordSql = "SELECT SUM(p.at_bat) AS total_at_bat,SUM(p.hit) AS total_hit,SUM(p.out) AS total_out FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";
        RowMapper<TotalDto> totalRecordMapper = new RowMapper<TotalDto>() {
            @Override
            public TotalDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TotalDto.Builder()
                        .totalAtBat(rs.getInt("total_at_bat"))
                        .totalHit(rs.getInt("total_hit"))
                        .totalOutCount(rs.getInt("total_out"))
                        .build();
            }
        };
        return jdbcTemplate.queryForObject(findTeamRecordSql, new Object[]{teamId}, totalRecordMapper);
    }

    public Long findOppositTeamByTeamId(Long teamId) {
        String findOppositSql = "SELECT team.id FROM team WHERE team.game = (SELECT team.game FROM team WHERE team.id = ?) AND team.id != ?";
        RowMapper<Long> teamIdMapper = new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("id");
            }
        };
        return jdbcTemplate.queryForObject(findOppositSql, new Object[]{teamId,teamId}, teamIdMapper);
    }

    public String findTeamNameByTeamId(Long teamId) {
        String findTeamNameSql = "SELECT team.name FROM team WHERE team.id = ?";
        RowMapper<String> teamNameMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name");
            }
        };
        return jdbcTemplate.queryForObject(findTeamNameSql,new Object[]{teamId}, teamNameMapper);
    }

    public String findUserIdByTeamId(Long teamId) {
        String findUserIdSql = "SELECT team.user_id FROM team WHERE team.id = ?";
        RowMapper<String> userIdMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("user_id");
            }
        };
        return jdbcTemplate.queryForObject(findUserIdSql,new Object[]{teamId}, userIdMapper);
    }

}
