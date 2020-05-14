package dev.codesquad.java.baseball08.dao;

import dev.codesquad.java.baseball08.dto.dto.HitterDto;
import dev.codesquad.java.baseball08.dto.dto.PitcherDto;
import dev.codesquad.java.baseball08.dto.dto.ScoreDto;
import dev.codesquad.java.baseball08.dto.dto.TeamIdAndTurnDto;
import dev.codesquad.java.baseball08.dto.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDaoHenry {
    private Logger logger = LoggerFactory.getLogger(GameDaoHenry.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TeamDaoHenry teamDaoHenry;

    @Autowired
    public GameDaoHenry(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 시작 화면. 전체 게임 리스트 및 팀 가져오기
    public List<GameListResponse> findAllGame() {
        String sql = "SELECT g.id AS game_id, GROUP_CONCAT(t.id ORDER BY t.game_key) AS team_id," +
                " GROUP_CONCAT(t.name ORDER BY t.game_key) AS team_name," +
                " GROUP_CONCAT(COALESCE(t.user_id, 'none') ORDER BY t.game_key) AS team_user" +
                " FROM game g" +
                " LEFT JOIN team t ON g.id = t.game" +
                " GROUP BY g.id";

        return jdbcTemplate.query(sql, new Object[] {}, (rs, rowNum) ->
                GameListResponse.builder()
                        .game(rs.getInt("game_id"))
                        .awayId(Long.parseLong(rs.getString("team_id").split(",")[0]))
                        .homeId(Long.parseLong(rs.getString("team_id").split(",")[1]))
                        .away(rs.getString("team_name").split(",")[0])
                        .home(rs.getString("team_name").split(",")[1])
                        .awayUser(rs.getString("team_user").split(",")[0])
                        .homeUser(rs.getString("team_user").split(",")[1])
                        .build()
        );
    }

    // gameId로 게임 선택 가능 여부 가져오기
    public AvailabilityResponse IsGameAvailable(Long id) {
        String sql = "SELECT on_game FROM game g WHERE g.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs, rowNum) ->
                new AvailabilityResponse(rs.getBoolean("on_game"))
        );
    }

    // gameId로 게임 진행화면 공통부분 가져오기
    public GamePlayResponse findGameInfoById(Long id) {
        String sql = "SELECT GROUP_CONCAT(DISTINCT t.name) AS team_name," +
                " GROUP_CONCAT(DISTINCT g.away_total_score) AS away_total_score," +
                " GROUP_CONCAT(DISTINCT g.home_total_score) AS home_total_score," +
                " GROUP_CONCAT(DISTINCT t.user_id) AS users," +
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
                        .awayUser(rs.getString("users").split(",")[0])
                        .homeUser(rs.getString("users").split(",")[1])
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

    // gameId, teamId로 게임 진행화면 해당 팀부분 가져오기
    public GamePlayResponse findGameTeamInfoById(Long id, Long teamId) {
        String sql = "SELECT GROUP_CONCAT(DISTINCT t.user_id) AS user_id," +
                " GROUP_CONCAT(DISTINCT t.pitcher) AS pitcher," +
                " GROUP_CONCAT(DISTINCT p.pitches) AS pitches," +
                " GROUP_CONCAT(DISTINCT t.hitter) AS hitter," +
                " GROUP_CONCAT(DISTINCT p.at_bat) AS at_bat," +
                " GROUP_CONCAT(DISTINCT p.hit) AS hit" +
                " FROM game g" +
                " INNER JOIN team t ON g.id = t.game" +
                " INNER JOIN player p ON t.id = p.team" +
                " WHERE g.id = ? and t.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {id, teamId}, (rs, rowNum) ->
                GamePlayResponse.builder()
                        .pitcher(PitcherDto.builder()
                                .name(rs.getString("pitcher"))
                                .pitches(rs.getInt("pitches"))
                                .build())
                        .hitter(HitterDto.builder()
                                .name(rs.getString("hitter"))
                                .atBat(rs.getInt("at_bat"))
                                .hit(rs.getInt("hit"))
                                .build())
                        .history(teamDaoHenry.findHistoriesById(teamId))
                        .build()
        );
    }

    // teamId로 현재 타자 정보 가져오기
    public HitterDto findHitterById(Long teamId) {
        String sql = "SELECT DISTINCT t.current_hitter AS hitter, p.at_bat AS at_bat, p.hit AS hit" +
                " FROM team t" +
                " INNER JOIN player p ON t.id = p.team AND t.current_hitter = p.name" +
                " WHERE t.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {teamId}, (rs, rowNum) ->
                HitterDto.builder()
                        .name(rs.getString("hitter"))
                        .atBat(rs.getInt("at_bat"))
                        .hit(rs.getInt("hit"))
                        .build()
        );
    }

    // teamId로 현재 투수 정보 가져오기
    public PitcherDto findPitcherById(Long teamId) {
        String sql = "SELECT DISTINCT t.pitcher AS pitcher, t.pitches AS pitches" +
                " FROM team t" +
                " INNER JOIN player p ON t.id = p.team" +
                " WHERE t.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {teamId}, (rs, rowNum) ->
                PitcherDto.builder()
                        .name(rs.getString("pitcher"))
                        .pitches(rs.getInt("pitches"))
                        .build()
        );
    }

    // gameId, hittingTeamId, pitchingTeamId로 게임 진행화면 가져오기
    public GamePlayResponse findGameInfoById(Long id, Long hittingTeamId, Long pitchingTeamId) {
        String sql = "SELECT GROUP_CONCAT(DISTINCT t.name) AS team_name," +
                " GROUP_CONCAT(DISTINCT g.away_total_score) AS away_total_score," +
                " GROUP_CONCAT(DISTINCT g.home_total_score) AS home_total_score," +
                " GROUP_CONCAT(DISTINCT t.user_id) AS users," +
                " GROUP_CONCAT(DISTINCT g.current_inning) AS current_inning," +
                " GROUP_CONCAT(DISTINCT g.turn) AS turn," +
                " MAX(i.strike_count) AS strike_count," +
                " MAX(i.ball_count) AS ball_count," +
                " MAX(i.out_count) AS out_count," +
                " MAX(i.base_count) AS base_count" +
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
                        .awayUser(rs.getString("users").split(",")[0])
                        .homeUser(rs.getString("users").split(",")[1])
                        .inning(rs.getInt("current_inning"))
                        .turn(rs.getString("turn"))
                        .score(ScoreDto.builder()
                                .strike(rs.getInt("strike_count"))
                                .ball(rs.getInt("ball_count"))
                                .out(rs.getInt("out_count"))
                                .base(rs.getInt("base_count"))
                                .build())
                        .pitcher(findPitcherById(pitchingTeamId))
                        .hitter(findHitterById(hittingTeamId))
                        .history(teamDaoHenry.findHistoriesById(hittingTeamId))
                        .build()
        );
    }

    public TeamIdAndTurnDto findTeamIdAndTurnById(Long id) {
        String sql = "SELECT g.turn AS turn, GROUP_CONCAT(t.id) AS team_id" +
                " FROM game g" +
                " INNER JOIN team t ON g.id = t.game" +
                " WHERE g.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {id}, (rs, rowNum) ->
                TeamIdAndTurnDto.builder()
                        .awayId(Long.parseLong(rs.getString("team_id").split(",")[0]))
                        .homeId(Long.parseLong(rs.getString("team_id").split(",")[1]))
                        .turn(rs.getString("turn"))
                        .build()
                );
    }

    public void updateOnGame(Long gameId, boolean status) {
        String sql = "UPDATE game g SET on_game = ? WHERE g.id = ?";
        this.jdbcTemplate.update(sql, new Object[] {status, gameId});
    }
}
