DROP TABLE IF EXISTS java_quiz;
DROP TABLE IF EXISTS users;
# CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY,# DEFAULT, #nextval('global_seq'),
    chat_id    INTEGER UNIQUE                NOT NULL,
    name       VARCHAR(1024)                     NOT NULL,
    score      INTEGER             DEFAULT 0 NOT NULL,
    high_score INTEGER             DEFAULT 0 NOT NULL,
    bot_state  VARCHAR(1024)                       NOT NULL
);

CREATE TABLE java_quiz
(
    id             INTEGER PRIMARY KEY,
    question       VARCHAR(1024)   NOT NULL,
    answer_correct VARCHAR(1024)   NOT NULL,
    option1        VARCHAR(1024)   NOT NULL,
    option2        VARCHAR(1024)   NOT NULL,
    option3        VARCHAR(1024)   NOT NULL
);