DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS inning;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS player_log;

CREATE TABLE IF NOT EXISTS game
(
    id               INT NOT NULL AUTO_INCREMENT,
    on_game          BOOLEAN,
    home_total_score INT,
    away_total_score INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS inning
(
    id         INT NOT NULL AUTO_INCREMENT,
    home_score INT,
    away_score INT,
--     record
    out_count INT,
    strike_count INT,
    ball_count INT,
    base_count INT,

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
    at_bat   INT,
    hit      INT,
    out      INT,
    pitches  INT,
    average  DOUBLE,

    team     INT REFERENCES team (id),
    team_key INT,
    PRIMARY KEY (id)
);
