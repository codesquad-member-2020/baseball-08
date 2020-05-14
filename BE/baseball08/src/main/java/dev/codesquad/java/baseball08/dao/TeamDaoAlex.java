package dev.codesquad.java.baseball08.dao;

import dev.codesquad.java.baseball08.dto.dto.PlayersDto;
import dev.codesquad.java.baseball08.dto.response.PlayersResponse;
import dev.codesquad.java.baseball08.dto.dto.TotalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class TeamDaoAlex {
    private static final Logger logger = LoggerFactory.getLogger(TeamDaoAlex.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamDaoAlex(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayersResponse findTeamPlayerInfo(Long id) {
        String sql = "SELECT t.name, t.user_id," +
                "GROUP_CONCAT(CONCAT_WS(',',p.name,p.at_bat,p.hit,p.out_count,p.average,p.line_up) SEPARATOR '/') AS players,\n" +
                "SUM(p.at_bat) AS total_bat, SUM(p.hit) as total_hit, SUM(p.out_count) AS total_out\n" +
                "FROM player p LEFT JOIN team t ON p.team = t.id WHERE p.team = ?";

        RowMapper<PlayersResponse> mapper = new RowMapper<PlayersResponse>() {
            @Override
            public PlayersResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlayersResponse(
                        rs.getString("name"), rs.getString("user_id"),
                        playersParser(rs.getString("players").split("/")),
                        TotalDto.builder()
                                .bat(Integer.parseInt(rs.getString("total_bat")))
                                .hit(Integer.parseInt(rs.getString("total_hit")))
                                .out(Integer.parseInt(rs.getString("total_out")))
                                .build());
            }

            private List<PlayersDto> playersParser(String[] playersSplit) {
                List<String> players = Arrays.asList(playersSplit);
                List<PlayersDto> result = new ArrayList<>();
                for (String player : players) {
                    List<String> playerInfo = Arrays.asList(player.split(","));
                    result.add(PlayersDto.builder()
                            .name(playerInfo.get(0))
                            .atBat(Integer.parseInt(playerInfo.get(1)))
                            .hit(Integer.parseInt(playerInfo.get(2)))
                            .out(Integer.parseInt(playerInfo.get(3)))
                            .average(Double.parseDouble(playerInfo.get(4)))
                            .lineUp(Integer.parseInt(playerInfo.get(5)))
                            .build());
                }
                result.sort(Comparator.comparing(PlayersDto::getLineUp));
                return result;
            }
        };
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapper);
    }

    // 기존의 팀별 선수 데이터를 불러오는 메소드
    // 팀 id 를 입력받아서 해당 팀의 선수들을 순서대로 조회하도록 구현
    public List<PlayersDto> findPlayersByTeamId(Long id) {
        String sql = "SELECT p.name,p.at_bat,p.hit,p.out_count,p.average FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) ->
                PlayersDto.builder()
                        .name(rs.getString("name"))
                        .atBat(rs.getInt("at_bat"))
                        .hit(rs.getInt("hit"))
                        .out(rs.getInt("out_count"))
                        .average(rs.getDouble("average"))
                        .build());
    }

    public PlayersDto findPlayerByPlayerName(String playerName) {
        String sql = "SELECT p.name,p.at_bat,p.hit,p.out_count,p.average,p.line_up FROM player p WHERE p.name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{playerName}, (rs, rowNum) ->
                PlayersDto.builder()
                        .name(rs.getString("name"))
                        .atBat(rs.getInt("at_bat"))
                        .hit(rs.getInt("hit"))
                        .out(rs.getInt("out_count"))
                        .average(rs.getDouble("average"))
                        .lineUp(rs.getInt("line_up"))
                        .build());
    }

    // 기존의 팀별 전체 기록을 불러오는 메소드
    // 팀 id를 입력받아서 해당 팀의 총 타석, 안타, 아웃 카운트를 조회하도록 구현
    public TotalDto findTotalRecordByTeamId(Long id) {
        String sql = "SELECT SUM(p.at_bat) AS total_at_bat,SUM(p.hit) AS total_hit,SUM(p.out) AS total_out FROM team t INNER JOIN player p ON t.id = p.team WHERE t.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                TotalDto.builder()
                        .bat(rs.getInt("total_at_bat"))
                        .hit(rs.getInt("total_hit"))
                        .out(rs.getInt("total_out"))
                        .build());
    }

    // 팀 id를 입력받아 상대편 팀의 id를 가져오는 메소드
    public Long findOppositeTeamIdByMyTeamId(Long id) {
        String sql = "SELECT t.id FROM team t WHERE t.game = (SELECT t.game FROM team t WHERE t.id = ?) AND t.id != ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id, id}, (rs, rowNum) -> rs.getLong("id"));
    }

    // 팀 id를 입력받아 팀의 name을 가져오는 메소드
    public String findTeamNameByTeamId(Long id) {
        return jdbcTemplate.queryForObject("SELECT t.name FROM team t WHERE t.id = ?", new Object[]{id},
                (rs, rowNum) -> rs.getString("name"));
    }

    // 팀 id, 게임 id를 입력받아 해당 팀의 userID를 가져오는 메소드
    public String findUserIdByGameIdTeamId(Long game, Long id) {
        return jdbcTemplate.queryForObject("SELECT t.user_id FROM team t WHERE t.game = ? AND t.id = ?", new Object[]{game, id},
                (rs, rowNum) -> rs.getString("user_id"));
    }

    public String[] findHitterByTeamId(Long teamId) {
        String sql = "SELECT t.current_hitter , t.last_hitter FROM team t WHERE t.id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{teamId},
                (rs, rowNum) -> new String[] {rs.getString("current_hitter"),rs.getString("last_hitter")});
    }

    public String findNextHitterNameByLineup(Long teamId, int lineUp) {
        String sql = "SELECT p.name FROM player p WHERE p.team = ? AND p.line_up = ?";
        if (lineUp == 9){
            lineUp = 0;
        }
        lineUp++;
        return jdbcTemplate.queryForObject(sql, new Object[]{teamId,lineUp},(rs, rowNum) -> rs.getString("name"));
    }
}
