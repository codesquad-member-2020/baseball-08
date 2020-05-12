package dev.codesquad.java.baseball08.dao;

import dev.codesquad.java.baseball08.dto.PlayersDto;
import dev.codesquad.java.baseball08.dto.ResponsePlayersDto;
import dev.codesquad.java.baseball08.dto.TempDto;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class TeamDao {
    private static final Logger logger = LoggerFactory.getLogger(TeamDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ResponsePlayersDto findTeamPlayerInfo(Long id) {
        String sql = "select t.name, t.user_id," +
                "GROUP_CONCAT(CONCAT_WS(',',p.name,p.at_bat,p.hit,p.out_count,p.average) separator '/') as players,\n" +
                "sum(p.at_bat) as total_bat, sum(p.hit) as total_hit, sum(p.out_count) as total_out\n" +
                "from player p left join team t on p.team = t.id where p.team = ?";
        RowMapper<ResponsePlayersDto> tempMapper = new RowMapper<ResponsePlayersDto>() {
            @Override
            public ResponsePlayersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ResponsePlayersDto(
                        rs.getString("name"),rs.getString("user_id"),
                        playersParser(rs.getString("players").split("/")),
                        new TotalDto.Builder()
                                .totalAtBat(Integer.parseInt(rs.getString("total_bat")))
                                .totalHit(Integer.parseInt(rs.getString("total_hit")))
                                .totalOutCount(Integer.parseInt(rs.getString("total_out")))
                                .build());
            }

            private List<PlayersDto> playersParser(String[] playersSplit) {
                List<String> players = Arrays.asList(playersSplit);
                List<PlayersDto> result = new ArrayList<>();
                for (String player : players) {
                    List<String> playerInfo = Arrays.asList(player.split(","));
                    result.add(new PlayersDto.Builder()
                            .name(playerInfo.get(0))
                            .atBat(Integer.parseInt(playerInfo.get(1)))
                            .hit(Integer.parseInt(playerInfo.get(2)))
                            .outCount(Integer.parseInt(playerInfo.get(3)))
                            .average(Double.parseDouble(playerInfo.get(4)))
                            .build());
                }
                return result;
            }
        };
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, tempMapper);
    }

    // 기존의 팀별 선수 데이터를 불러오는 메소드
    // 팀 id 를 입력받아서 해당 팀의 선수들을 순서대로 조회하도록 구현
    public List<PlayersDto> findPlayersByTeamId(Long id) {
        String sql = "SELECT p.name,p.at_bat,p.hit,p.out,p.average FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<PlayersDto>() {
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

    // 기존의 팀별 전체 기록을 불러오는 메소드
    // 팀 id를 입력받아서 해당 팀의 총 타석, 안타, 아웃 카운트를 조회하도록 구현
    public TotalDto findTotalRecordByTeamId(Long id) {
        String sql = "SELECT SUM(p.at_bat) AS total_at_bat,SUM(p.hit) AS total_hit,SUM(p.out) AS total_out FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";
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
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, totalRecordMapper);
    }

    // 팀 id를 입력받아 상대편 팀의 id를 가져오는 메소드
    public Long findOppositTeamByTeamId(Long id) {
        String sql = "SELECT team.id FROM team WHERE team.game = (SELECT team.game FROM team WHERE team.id = ?) AND team.id != ?";
        RowMapper<Long> teamIdMapper = new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("id");
            }
        };
        return jdbcTemplate.queryForObject(sql, new Object[]{id,id}, teamIdMapper);
    }

    public String findTeamNameByTeamId(Long id) {
        String findTeamNameSql = "SELECT team.name FROM team WHERE team.id = ?";
        RowMapper<String> teamNameMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name");
            }
        };
        return jdbcTemplate.queryForObject(findTeamNameSql, new Object[]{id}, teamNameMapper);
    }

    public String findUserIdByTeamId(Long teamId) {
        String findUserIdSql = "SELECT team.user_id FROM team WHERE team.id = ?";
        RowMapper<String> userIdMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("user_id");
            }
        };
        return jdbcTemplate.queryForObject(findUserIdSql, new Object[]{teamId}, userIdMapper);
    }

}
