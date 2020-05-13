DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS inning;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS log;

CREATE TABLE IF NOT EXISTS game
(
    id               INT NOT NULL AUTO_INCREMENT,
    on_game          BOOLEAN     DEFAULT (false),
    home_total_score INT         DEFAULT (0),
    away_total_score INT         DEFAULT (0),
    current_inning   INT         DEFAULT (0),
    turn             VARCHAR(32) DEFAULT ('초'),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS inning
(
    id           INT NOT NULL AUTO_INCREMENT,
    home_name    VARCHAR(32),
    away_name    VARCHAR(32),

    home_attack  BOOLEAN DEFAULT false,
    away_attack  BOOLEAN DEFAULT false,

    home_score   INT     DEFAULT (0),
    away_score   INT     DEFAULT (0),

    strike_count INT     DEFAULT (0),
    ball_count   INT     DEFAULT (0),
    out_count    INT     DEFAULT (0),
    base_count   INT     DEFAULT (0),

    game         INT REFERENCES game (id),
    game_key     INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS team
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(32),
    user_id  VARCHAR(32),
    pitcher  VARCHAR(32),
    hitter   VARCHAR(32),

    game     INT REFERENCES game (id),
    game_key INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS player
(
    id        INT NOT NULL AUTO_INCREMENT,
    name      VARCHAR(32),
    line_up   INT,

    at_bat    INT DEFAULT (0),
    hit       INT DEFAULT (0),
    out_count INT DEFAULT (0),
    pitches   INT DEFAULT (0),
    average   DOUBLE,

    team      INT REFERENCES team (id),
    team_key  INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS history
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(32),
    line_up  INT,

    team     INT REFERENCES team (id),
    team_key INT,
    game     INT REFERENCES game (id),
    game_key INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS log
(
    id          INT NOT NULL AUTO_INCREMENT,
    hit_log     VARCHAR(32),

    history     INT REFERENCES history (id),
    history_key INT,
    PRIMARY KEY (id)
);
