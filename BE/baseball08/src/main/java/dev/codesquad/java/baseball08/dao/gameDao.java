package dev.codesquad.java.baseball08.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class GameDao {
    private static final Logger logger = LoggerFactory.getLogger(GameDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
