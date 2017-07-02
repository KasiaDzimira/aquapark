INSERT INTO days (name) VALUES ('Monday');
INSERT INTO days (name) VALUES ('Tuesday');
INSERT INTO days (name) VALUES ('Wednesday');
INSERT INTO days (name) VALUES ('Thursday');
INSERT INTO days (name) VALUES ('Friday');
INSERT INTO days (name) VALUES ('Saturday');
INSERT INTO days (name) VALUES ('Sunday');

INSERT INTO daytime (name, start_hour, end_hour) VALUES ('Morning', '7:00', '11:59:59');
INSERT INTO daytime (name, start_hour, end_hour) VALUES ('Afternoon', '12:00', '16:59:59');
INSERT INTO daytime (name, start_hour, end_hour) VALUES ('Evening', '17:00', '21:00');

INSERT INTO disc_group (name, discount) VALUES ('None', 0.0);
INSERT INTO disc_group (name, discount) VALUES ('Kids', 0.6);
INSERT INTO disc_group (name, discount) VALUES ('Students', 0.3);
INSERT INTO disc_group (name, discount) VALUES ('Elders', 0.4);

INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Hubert', 'Karewicz', 'hkarewicz@a.com', '111111111', 'hkarewicz', 'karewicz1234', 'ROLE_USER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Jolanta', 'Nowak', 'jnow@b.com', '222222222', 'jnow', 'nowak1234', 'ROLE_USER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Robert', 'Kowalski', 'rycerz1@c.pl', '333333333', 'rycerz', 'kowalski1234', 'ROLE_USER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Jacek', 'Rybak', 'ryby@ryby.ru', '444444444', 'ryby@ryby', 'ryby1234', 'ROLE_USER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Monika', 'Dołęga', 'monisia@cmok.pl', '555555555', 'monisia@cmok', 'cmok1234', 'ROLE_USER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Tester', 'Admin', 'test@test.com', '555555555', 'TesterAdmin', '12345', 'ROLE_ADMIN');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Tester', 'Manager', 'test@test.com', '555555555', 'TesterManager', '12345', 'ROLE_MANAGER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Tester', 'Testowy', 'test@test.com', '555555555', 'TesterTestowy', '12345', 'ROLE_USER');
INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user) VALUES ('Tester', 'Kasjer', 'test@test.com', '555555555', 'TesterKasjer', '12345', 'ROLE_EMPLOYEE');

-- assuming 1 - in use, 0 - damaged, won't be used
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (0);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (0);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (0);
INSERT INTO watch (status) VALUES (1);
INSERT INTO watch (status) VALUES (1);

INSERT INTO pass_type (name) VALUES ('Basic');
INSERT INTO pass_type (name) VALUES ('Extended');

INSERT INTO attraction_type (name) VALUES ('Basic Zone');
INSERT INTO attraction_type (name) VALUES ('Pool');
INSERT INTO attraction_type (name) VALUES ('Slide');
INSERT INTO attraction_type (name) VALUES ('Sauna');

INSERT INTO attraction (name, status, attraction_type_id) VALUES ('Sport Pool', true, (SELECT id from attraction_type WHERE name='Pool'));
INSERT INTO attraction (name, status, attraction_type_id) VALUES ('Red Slide', true, (SELECT id from attraction_type WHERE name='Slide'));
INSERT INTO attraction (name, status, attraction_type_id) VALUES ('Green Slide', false, (SELECT id from attraction_type WHERE name='Slide'));
INSERT INTO attraction (name, status, attraction_type_id) VALUES ('Yellow Slide', true, (SELECT id from attraction_type WHERE name='Slide'));
INSERT INTO attraction (name, status, attraction_type_id) VALUES ('Turkish Sauna', true, (SELECT id from attraction_type WHERE name='Sauna'));

INSERT INTO pass (start_date, end_date, aquapark_user_id, pass_type_id, disc_group_id) VALUES
('2017-04-20', '2017-04-22', (SELECT id from aquapark_user WHERE email='jnow@b.com'), (SELECT id from pass_type WHERE name='Basic'), (SELECT id from disc_group WHERE name='None'));
INSERT INTO pass (start_date, end_date, aquapark_user_id, pass_type_id, disc_group_id) VALUES
('2017-03-27', '2017-04-02', (SELECT id from aquapark_user WHERE email='ryby@ryby.ru'), (SELECT id from pass_type WHERE name='Basic'), (SELECT id from disc_group WHERE name='Students'));
INSERT INTO pass (start_date, end_date, aquapark_user_id, pass_type_id, disc_group_id) VALUES
('2017-04-10', '2017-05-08', (SELECT id from aquapark_user WHERE email='ryby@ryby.ru'), (SELECT id from pass_type WHERE name='Extended'), (SELECT id from disc_group WHERE name='Students'));

INSERT INTO ticket (stamp, stamp_out, watch_id) VALUES ('2017-04-20 10:23:54', '2017-04-20 12:23:54', 1);
INSERT INTO ticket (stamp, stamp_out, watch_id) VALUES ('2017-04-20 10:25:17', '2017-04-20 14:25:17', 2);
INSERT INTO ticket (stamp, stamp_out, watch_id, pass_id) VALUES ('2017-04-20 12:41:22', '2017-04-20 16:41:22', 4, (SELECT id FROM pass WHERE aquapark_user_id = (SELECT id from aquapark_user WHERE email='jnow@b.com')));
INSERT INTO ticket (stamp, stamp_out, watch_id) VALUES ('2017-04-20 14:19:07', '2017-04-20 18:19:07', 8);
INSERT INTO ticket (stamp, stamp_out, watch_id, pass_id) VALUES ('2017-04-21 08:10:19', '2017-04-21 09:40:19', 1, (SELECT id FROM pass WHERE aquapark_user_id = (SELECT id from aquapark_user WHERE email='jnow@b.com')));
INSERT INTO ticket (stamp, stamp_out, watch_id) VALUES ('2017-04-21 08:14:22', '2017-04-21 10:14:22', 10);

INSERT INTO history (entry_time, exit_time, attraction_id, watch_id) VALUES ('2017-04-20 10:31:16', '2017-04-20 10:51:49', (SELECT id from attraction WHERE name='Sport Pool'), 1);
INSERT INTO history (entry_time, exit_time, attraction_id, watch_id) VALUES ('2017-04-20 10:31:44', '2017-04-20 10:51:28', (SELECT id from attraction WHERE name='Sport Pool'), 2);
INSERT INTO history (entry_time, exit_time, attraction_id, watch_id) VALUES ('2017-04-20 10:58:30', '2017-04-20 11:15:13', (SELECT id from attraction WHERE name='Red Slide'), 1);
INSERT INTO history (entry_time, exit_time, attraction_id, watch_id) VALUES ('2017-04-21 08:36:09', '2017-04-21 08:44:02', (SELECT id from attraction WHERE name='Turkish Sauna'), 1);
INSERT INTO history (entry_time, exit_time, attraction_id, watch_id) VALUES ('2017-04-21 08:52:11', '2017-04-21 09:38:55', (SELECT id from attraction WHERE name='Turkish Sauna'), 1);

INSERT INTO tckt_prc_lst (start_date, end_date) VALUES ('2017-03-01', '2017-03-31');
INSERT INTO tckt_prc_lst (start_date, end_date) VALUES ('2017-04-01', '2017-05-31');

INSERT INTO pass_prc_lst (start_date, end_date) VALUES ('2017-01-01', '2017-03-31');
INSERT INTO pass_prc_lst (start_date, end_date) VALUES ('2017-04-01', '2017-12-31');

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	5.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	5.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	6.5,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	6.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	6.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	8.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	10.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	10.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	12.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	13.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	13.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	16.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	4.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	4.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	5.5,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	5.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	5.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	7.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Pool')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	9.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	9.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	11.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Morning'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	12.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Monday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	12.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Tuesday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (
	15.0,
	(SELECT id from tckt_prc_lst WHERE start_date='2017-03-01'),
	(SELECT id from days WHERE name='Saturday'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from daytime WHERE name='Afternoon'),
	(SELECT id from attraction_type WHERE name='Slide')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	23.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from disc_group WHERE name='Students'),
	(SELECT id from pass_type WHERE name='Basic')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	40.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from disc_group WHERE name='Students'),
	(SELECT id from pass_type WHERE name='Extended')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	21.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-01-01'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from pass_type WHERE name='Basic')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	37.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-01-01'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from pass_type WHERE name='Extended')
);
INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	18.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-01-01'),
	(SELECT id from disc_group WHERE name='Students'),
	(SELECT id from pass_type WHERE name='Basic')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	35.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-01-01'),
	(SELECT id from disc_group WHERE name='Students'),
	(SELECT id from pass_type WHERE name='Extended')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	26.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from pass_type WHERE name='Basic')
);

INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id) VALUES (
	42.0,
	(SELECT id from pass_prc_lst WHERE start_date='2017-04-01'),
	(SELECT id from disc_group WHERE name='None'),
	(SELECT id from pass_type WHERE name='Extended')
);