BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--users test data (5 users)
--TestBro pw TestBro
INSERT INTO users (username,password_hash,role) VALUES ('TestBro', '$2a$10$4DwQe/APC1vgRa6nRsQwq.QfE2qzX8JZ7/9o.L9CcRLOldwgAFxCu', 'ROLE_USER');
--TestJim pw TestJim
INSERT INTO users (username,password_hash,role) VALUES ('TestJim', '$2a$10$d6Eo1n/MTfWGv85BykyOcONtMGZbdC1ocIT7mwc/6aIWKO.zPblD.', 'ROLE_USER');
--TestBob pw TestBob
INSERT INTO users (username,password_hash,role) VALUES ('TestBob', '$2a$10$ZaRF6OFMc4guoHaubmzZX.PioWE8miQnt61ZsC583dMKWLMfolkHa', 'ROLE_USER');


--sports test data (6 sports)
INSERT INTO public.sports(sport_name)
VALUES ('Soccer'), ('Football'), ('Golf'), ('Starcraft'), ('League of Legends'), ('Poker');

--request_status test data (3 statuses)
--INSERT INTO public.request_status(request_status, request_status_text)
--VALUES (1, 'Pending'), (2, 'Approved'), (3, 'Denied');

--teams test data (3 teams)
INSERT INTO public.teams(
	captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation)
	VALUES (3, 'testTeam1', 1, true, true, 25, 'testteam1@team.com', 'Ashland', 'OR');
INSERT INTO public.teams(
	captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation)
	VALUES (3, 'testTeam2', 1, true, true, 25, 'testteam2@team.com', 'Columbus', 'OH');
INSERT INTO public.teams(
	captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation)
	VALUES (3, 'testTeam2', 1, false, false, 25, 'testteam2@team.com', 'Columbus', 'OH');

--tournament_format test data (5 formats)
INSERT INTO public.tournament_format(
	format_type)
	VALUES ('Single Elimination'),
	('Single Elimination'),
	('Best of Three'),
	('League'),
	('Round Robin');

--tournaments test data (3 tourneys)
INSERT INTO public.tournaments(
	host_id, tournament_name, sport_id, approval_to_join, tournament_timestamp, max_teams, format_id,
	 host_email, tournament_city, tournament_state_abbreviation, tournament_prize, tournament_winner )
	VALUES (2, 'The Final Countdown', 4, true, '2022-09-20 04:05:06', 20, 1, 'me@email.com', 'Atlanta', 'GA', '$500', 2),
	(3, 'Fooball!', 4, true, '2022-10-20 04:05:06', 20, 2, 'fooball@email.com', 'Cleveland', 'OH', '$1000', 1),
	(3, 'Baccarat', 4, false, '2022-11-20 04:05:06', 30, 1, 'poker@email.com', 'Pittsburg', 'PA', 'bragging rights', 3);

--matches test data
INSERT INTO public.matches(
	tournament_id, match_timestamp, match_address, winning_team, results)
	VALUES (1, '2022-09-20 04:05:06', '42 Wallaby Way, Sydney', 2, null);
INSERT INTO public.matches(
    tournament_id, match_timestamp, match_address, winning_team, results)
    VALUES (2, '2022-10-20 04:05:06', '42 Wallaby Way, Sydney', 1, 'Crushing Victory for testTeam1');
INSERT INTO public.matches(
	tournament_id, match_timestamp, match_address, winning_team, results)
	VALUES (3, '2022-11-20 04:05:06', '42 Wallaby Way, Sydney', 3, 'Narrow win for testTeam3');

--match_points test data
INSERT INTO public.match_points(match_id, team_id, points)
	VALUES (1, 2, null);
INSERT INTO public.match_points(match_id, team_id, points)
    VALUES (2, 1, 3);
INSERT INTO public.match_points(match_id, team_id, points)
	VALUES (3, 3, 3);

--tournament_points test data
INSERT INTO public.tournament_points(
	tournament_id, team_id, points)
	VALUES (1, 2, 5);
INSERT INTO public.tournament_points(
	tournament_id, team_id, points)
	VALUES (2, 1, 10);
INSERT INTO public.tournament_points(
	tournament_id, team_id, points)
	VALUES (3, 3, 1);

--team_matches test data
--match 1
INSERT INTO public.team_matches(
	match_id, team_id)
	VALUES (1, 1);
INSERT INTO public.team_matches(
	match_id, team_id)
	VALUES (1, 2);
--match 2
INSERT INTO public.team_matches(
	match_id, team_id)
	VALUES (2, 1);
INSERT INTO public.team_matches(
	match_id, team_id)
	VALUES (2, 3);
--match 3
INSERT INTO public.team_matches(
	match_id, team_id)
	VALUES (2, 2);
INSERT INTO public.team_matches(
	match_id, team_id)
	VALUES (2, 3);

--team_tournaments test data
INSERT INTO public.team_tournaments(
team_id, tournament_id)
VALUES (1, 1), (2, 2), (3, 3);

--request_join_team test data
INSERT INTO public.request_join_team(
	team_id, user_id, request_status, request_timestamp)
	VALUES (1, 4, 'Pending', '2022-10-20 04:05:06');
INSERT INTO public.request_join_team(
	team_id, user_id, request_status, request_timestamp)
	VALUES (2, 5, 'Approved', '2022-10-20 04:05:06');
INSERT INTO public.request_join_team(
	team_id, user_id, request_status, request_timestamp)
	VALUES (3, 5, 'Denied', '2022-10-20 04:05:06');

--request_join_tournament test data
INSERT INTO public.request_join_tournament(
	team_id, tournament_id, request_status, request_timestamp)
	VALUES (1, 1, 'Approved', '2022-10-20T11:05:06.000+00:00');
INSERT INTO public.request_join_tournament(
	team_id, tournament_id, request_status, request_timestamp)
	VALUES (2, 2, 'Denied', '2022-10-20T11:05:06.000+00:00');
INSERT INTO public.request_join_tournament(
	team_id, tournament_id, request_status, request_timestamp)
	VALUES (3, 3, 'Approved', '2022-10-20T11:05:06.000+00:00');

--user_teams test data
INSERT INTO public.user_teams(
	user_id, team_id)
	VALUES (4, 1);
INSERT INTO public.user_teams(
	user_id, team_id)
	VALUES (5, 2);
INSERT INTO public.user_teams(
	user_id, team_id)
	VALUES (4, 3);

COMMIT TRANSACTION;
