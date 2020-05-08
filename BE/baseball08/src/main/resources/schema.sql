DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS inning;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS player_log;

CREATE TABLE IF NOT EXISTS game
(
    id               INT NOT NULL AUTO_INCREMENT,
    on_game          BOOLEAN DEFAULT (false),
    home_total_score INT DEFAULT (0),
    away_total_score INT DEFAULT (0),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS inning
(
    id         INT NOT NULL AUTO_INCREMENT,
    home_score INT DEFAULT (0),
    away_score INT DEFAULT (0),
--     record
    out_count INT DEFAULT (0),
    strike_count INT DEFAULT (0),
    ball_count INT DEFAULT (0),
    base_count INT DEFAULT (0),

    game       INT REFERENCES game (id),
    game_key   INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS player_log
(
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS team
(
    id             INT NOT NULL AUTO_INCREMENT,
    name           VARCHAR(32),
    user_id        VARCHAR(32),
    current_player VARCHAR(32),

    game           INT REFERENCES game (id),
    game_key       INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS player
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(32),
    line_up  INT,
--     record
    at_bat   INT DEFAULT (0),
    hit      INT DEFAULT (0),
    out      INT DEFAULT (0),
    pitches  INT DEFAULT (0),
    average  DOUBLE,

    team     INT REFERENCES team (id),
    team_key INT,
    PRIMARY KEY (id)
);
