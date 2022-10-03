BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS sports;
CREATE TABLE sports(
sport_id SERIAL,
sport_name VARCHAR(50),
PRIMARY KEY(sport_id)
);

--DROP TABLE IF EXISTS request_status;
--CREATE TABLE request_status(
--request_status int NOT NULL,
--request_status_text VARCHAR(10),
--PRIMARY KEY(request_status)
--);

DROP TABLE IF EXISTS teams;
CREATE TABLE teams(
team_id SERIAL,
captain_id int NOT NULL,
team_name VARCHAR(50) NOT NULL,
sport_id int NOT NULL,
team_open_to_new_members boolean NOT NULL,
approval_to_join boolean NOT NULL,
max_members int,
team_email VARCHAR(50),
team_city VARCHAR(50),
team_state_abbreviation VARCHAR(10),
PRIMARY KEY(team_id),
CONSTRAINT FK_team_captain FOREIGN KEY (captain_id) REFERENCES users(user_id),
CONSTRAINT FK_team_sport FOREIGN KEY (sport_id) REFERENCES sports(sport_id)
);

DROP TABLE IF EXISTS tournament_format;
CREATE TABLE tournament_format(
format_id SERIAL,
format_type VARCHAR(50) NOT NULL,
PRIMARY KEY (format_id)
);

DROP TABLE IF EXISTS tournaments;
CREATE TABLE tournaments(
tournament_id SERIAL,
host_id int NOT NULL,
tournament_name VARCHAR(50) NOT NULL,
sport_id int NOT NULL,
approval_to_join boolean NOT NULL,
tournament_timestamp TIMESTAMP,
max_teams int,
format_id int NOT NULL,
host_email VARCHAR(50),
tournament_city VARCHAR(50),
tournament_state_abbreviation VARCHAR(10),
tournament_prize VARCHAR(200),
tournament_winner int,
PRIMARY KEY(tournament_id),
CONSTRAINT FK_tournament_host FOREIGN KEY (host_id) REFERENCES users(user_id),
CONSTRAINT FK_team_sport FOREIGN KEY (sport_id) REFERENCES sports(sport_id),
CONSTRAINT FK_tournament_format FOREIGN KEY (format_id ) REFERENCES tournament_format(format_id)
);


DROP TABLE IF EXISTS matches;
CREATE TABLE matches(
match_id SERIAL,
tournament_id int NOT NULL,
match_timestamp TIMESTAMP,
match_address VARCHAR(200) NOT NULL,
winning_team int,
results VARCHAR(200),
PRIMARY KEY (match_id),
CONSTRAINT FK_match_tournament FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id)
);

DROP TABLE IF EXISTS match_points;
CREATE TABLE match_points(
match_point_id SERIAL,
match_id int,
team_id int,
points int,
PRIMARY KEY (match_point_id),
CONSTRAINT FK_match_point FOREIGN KEY (match_id) REFERENCES matches(match_id),
CONSTRAINT FK_team_point FOREIGN KEY (team_id) REFERENCES teams(team_id)
);

DROP TABLE IF EXISTS tournament_points;
CREATE TABLE tournament_points(
tournament_point_id SERIAL,
tournament_id int,
team_id int,
points int,
PRIMARY KEY (tournament_point_id),
CONSTRAINT FK_tournament_point FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id),
CONSTRAINT FK_team_point FOREIGN KEY (team_id) REFERENCES teams(team_id)
);

DROP TABLE IF EXISTS team_matches;
CREATE TABLE team_matches(
match_id int,
team_id int,
CONSTRAINT FK_match FOREIGN KEY (match_id) REFERENCES matches(match_id),
CONSTRAINT FK_team FOREIGN KEY (team_id) REFERENCES teams(team_id)
);

DROP TABLE IF EXISTS team_tournaments;
CREATE TABLE team_tournaments(
team_id int,
tournament_id int,
CONSTRAINT FK_teams_in FOREIGN KEY (team_id) REFERENCES teams(team_id),
CONSTRAINT FK_tournament_in FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id)
);

DROP TABLE IF EXISTS request_join_team;
CREATE TABLE request_join_team(
request_id SERIAL,
team_id int,
user_id int,
request_status VARCHAR(10),
request_timestamp TIMESTAMP,
PRIMARY KEY (request_id),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT FK_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id)
--CONSTRAINT FK_user_status FOREIGN KEY (request_status) REFERENCES request_status(request_status)
);

DROP TABLE IF EXISTS request_join_tournament;
CREATE TABLE request_join_tournament(
request_id SERIAL,
team_id int NOT NULL,
tournament_id int NOT NULL,
request_status VARCHAR(10),
request_timestamp TIMESTAMP,
PRIMARY KEY (request_id),
CONSTRAINT FK_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id),
CONSTRAINT FK_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id)
--CONSTRAINT FK_user_status FOREIGN KEY (request_status) REFERENCES request_status(request_status)
);


DROP TABLE IF EXISTS user_teams;
CREATE TABLE user_teams(
user_id int,
team_id int,
CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT FK_team FOREIGN KEY (team_id) REFERENCES teams(team_id)
);

COMMIT TRANSACTION;
