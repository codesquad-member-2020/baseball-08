package dev.codesquad.java.baseball08.dao;

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
public class GameDao {
    private static final Logger logger = LoggerFactory.getLogger(GameDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<String> getGameUserId(Long game) {
        String sql = "SELECT t.user_id FROM team t WHERE t.game = ?";
        return jdbcTemplate.query(sql, new Object[]{game}, (rs, rowNum) -> rs.getString("user_id"));
    }

}
