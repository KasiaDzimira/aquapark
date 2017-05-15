CREATE TABLE watch (
	id		SERIAL NOT NULL,
	status 	INTEGER
	-- Should be a kind of enum
);

ALTER TABLE watch ADD CONSTRAINT watch_pk PRIMARY KEY ( id );

CREATE TABLE ticket (
	id			SERIAL NOT NULL,
	stamp 		TIMESTAMP,
	watch_id	INTEGER NOT NULL
);

ALTER TABLE ticket ADD CONSTRAINT ticket_pk PRIMARY KEY ( id );

CREATE TABLE history (
	id				SERIAL NOT NULL,
	entry_time		TIMESTAMP,
	exit_time		TIMESTAMP,
	attraction_id	INTEGER NOT NULL,
	watch_id		INTEGER NOT NULL
);

ALTER TABLE history ADD CONSTRAINT history_pk PRIMARY KEY ( id );

CREATE TABLE pass (
	id				SERIAL NOT NULL,
	start_date		DATE,
	end_date		DATE,
	watch_id		INTEGER NOT NULL,
	aquapark_user_id			INTEGER NOT NULL,
	pass_type_id	INTEGER NOT NULL
);

ALTER TABLE pass ADD CONSTRAINT pass_pk PRIMARY KEY ( id );

CREATE TABLE pass_type (
	id		SERIAL NOT NULL,
	name	VARCHAR(20)
);

ALTER TABLE pass_type ADD CONSTRAINT pass_type_pk PRIMARY KEY ( id );

CREATE TABLE aquapark_user (
	id			SERIAL NOT NULL,
	first_name	VARCHAR(20),
	last_name	VARCHAR(30),
	email		VARCHAR(30),
	telephone	VARCHAR(12),
	nick		VARCHAR(20),
	password	VARCHAR(20),
	role_user	VARCHAR(20)
);

ALTER TABLE aquapark_user ADD CONSTRAINT aquapark_useruser_pk PRIMARY KEY ( id );

CREATE TABLE attraction (
	id					SERIAL NOT NULL,
	status				INTEGER,
	-- Should be a kind of enum
	attraction_type_id	INTEGER NOT NULL
);

ALTER TABLE attraction ADD CONSTRAINT attraction_pk PRIMARY KEY ( id );

CREATE TABLE attraction_type (
	id		SERIAL NOT NULL,
	name	VARCHAR(25)
);

ALTER TABLE attraction_type ADD CONSTRAINT attraction_type_pk PRIMARY KEY ( id );

CREATE TABLE daytime (
	id			SERIAL NOT NULL,
	name		VARCHAR(15),
	start_hour	TIME,
	end_hour	TIME
);

ALTER TABLE daytime ADD CONSTRAINT daytime_pk PRIMARY KEY ( id );

CREATE TABLE disc_group (
	id			SERIAL NOT NULL,
	name		VARCHAR(20),
	discount	REAL
);

ALTER TABLE disc_group ADD CONSTRAINT disc_group_pk PRIMARY KEY ( id );

CREATE TABLE days (
	id		SERIAL NOT NULL,
	name	VARCHAR(20)
);

ALTER TABLE days ADD CONSTRAINT days_pk PRIMARY KEY ( id );

CREATE TABLE tckt_prc_lst (
	id			SERIAL NOT NULL,
	start_date	DATE,
	end_date	DATE
);

ALTER TABLE tckt_prc_lst ADD CONSTRAINT tckt_prc_lst_pk PRIMARY KEY ( id );

CREATE TABLE pass_prc_lst (
	id			SERIAL NOT NULL,
	start_date	DATE,
	end_date	DATE
);

ALTER TABLE pass_prc_lst ADD CONSTRAINT pass_prc_lst_pk PRIMARY KEY ( id );

CREATE TABLE tckt_prc_lst_pos (
	id					SERIAL NOT NULL,
	price				NUMERIC,
	tckt_prc_lst_id		INTEGER NOT NULL,
	days_id				INTEGER NOT NULL,
	disc_group_id		INTEGER NOT NULL,
	daytime_id			INTEGER NOT NULL,
	attraction_type_id	INTEGER NOT NULL
);

ALTER TABLE tckt_prc_lst_pos ADD CONSTRAINT tckt_prc_lst_pos_pk PRIMARY KEY ( id, tckt_prc_lst_id,
days_id, disc_group_id, daytime_id, attraction_type_id );

CREATE TABLE pass_prc_lst_pos (
	id					SERIAL NOT NULL,
	price				NUMERIC,
	pass_prc_lst_id		INTEGER NOT NULL,
	disc_group_id		INTEGER NOT NULL,
	pass_type_id		INTEGER NOT NULL,
	attraction_type_id	INTEGER NOT NULL
);

ALTER TABLE pass_prc_lst_pos ADD CONSTRAINT pass_prc_lst_pos_pk PRIMARY KEY ( id, pass_prc_lst_id,
disc_group_id, pass_type_id, attraction_type_id );

ALTER TABLE ticket ADD CONSTRAINT tckt_watch_fk FOREIGN KEY ( watch_id ) REFERENCES watch ( id );

ALTER TABLE history ADD CONSTRAINT hist_watch_fk FOREIGN KEY ( watch_id ) REFERENCES watch ( id );

ALTER TABLE history ADD CONSTRAINT hist_attraction_fk FOREIGN KEY ( attraction_id ) REFERENCES attraction ( id );

ALTER TABLE pass ADD CONSTRAINT pass_watch_fk FOREIGN KEY ( watch_id ) REFERENCES watch ( id );

ALTER TABLE pass ADD CONSTRAINT pass_aquapark_user_fk FOREIGN KEY ( aquapark_user_id ) REFERENCES aquapark_user ( id );

ALTER TABLE pass ADD CONSTRAINT pass_pass_type_fk FOREIGN KEY ( pass_type_id ) REFERENCES pass_type ( id );

ALTER TABLE attraction ADD CONSTRAINT attr_attraction_type_fk FOREIGN KEY ( attraction_type_id ) REFERENCES attraction_type ( id );

ALTER TABLE tckt_prc_lst_pos ADD CONSTRAINT tckt_pos_tckt_prc_lst_fk FOREIGN KEY ( tckt_prc_lst_id ) REFERENCES tckt_prc_lst ( id );

ALTER TABLE tckt_prc_lst_pos ADD CONSTRAINT tckt_pos_days_fk FOREIGN KEY ( days_id ) REFERENCES days ( id );

ALTER TABLE tckt_prc_lst_pos ADD CONSTRAINT tckt_pos_disc_group_fk FOREIGN KEY ( disc_group_id ) REFERENCES disc_group ( id );

ALTER TABLE tckt_prc_lst_pos ADD CONSTRAINT tckt_pos_daytime_fk FOREIGN KEY ( daytime_id ) REFERENCES daytime ( id );

ALTER TABLE tckt_prc_lst_pos ADD CONSTRAINT tckt_pos_attraction_type_fk FOREIGN KEY ( attraction_type_id ) REFERENCES attraction_type ( id );

ALTER TABLE pass_prc_lst_pos ADD CONSTRAINT pass_pos_pass_prc_lst_fk FOREIGN KEY ( pass_prc_lst_id ) REFERENCES pass_prc_lst ( id );

ALTER TABLE pass_prc_lst_pos ADD CONSTRAINT pass_pos_disc_group_fk FOREIGN KEY ( disc_group_id ) REFERENCES disc_group ( id );

ALTER TABLE pass_prc_lst_pos ADD CONSTRAINT pass_pos_pass_type_fk FOREIGN KEY ( pass_type_id ) REFERENCES pass_type ( id );

ALTER TABLE pass_prc_lst_pos ADD CONSTRAINT pass_pos_attraction_type_fk FOREIGN KEY ( attraction_type_id ) REFERENCES attraction_type ( id );