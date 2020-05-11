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
        String findPlayerSQL = "select p.name,p.at_bat,p.hit,p.out_count,p.average from team t INNER JOIN player p on t.id = p.team where t.id = ?";
        return jdbcTemplate.query(findPlayerSQL, new Object[]{teamId}, new RowMapper<PlayersDto>() {
            @Override
            public PlayersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlayersDto.Builder()
                        .name(rs.getString("name"))
                        .atBat(rs.getInt("at_bat"))
                        .hit(rs.getInt("hit"))
                        .outCount(rs.getInt("out_count"))
                        .average(rs.getDouble("average"))
                        .build();
            }
        });
    }


}
