package dev.codesquad.java.baseball08.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class HistoryDaoHenry {
    private Logger logger = LoggerFactory.getLogger(HistoryDaoHenry.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HistoryDaoHenry(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveHistory(Long teamId, String currentHitter, Integer lineUp, String log) {
        String historySql = "INSERT INTO history (name, line_up, log, team) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(historySql, new Object[]{currentHitter, lineUp, log, teamId});
    }

    public void saveLog(String hitLog, Long historyId) {
        String logSql = "INSERT INTO log (hit_log, history) VALUES  (?, ?)";
        jdbcTemplate.update(logSql, new Object[]{hitLog, historyId});
    }
}
