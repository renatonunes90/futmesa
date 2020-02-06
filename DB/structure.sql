﻿/********************* UDFS ***********************/

/****************** GENERATORS ********************/

CREATE GENERATOR CHAMPIONSHIP_GEN;
CREATE GENERATOR GAME_GEN;
CREATE GENERATOR GROUP_GEN;
CREATE GENERATOR PHASE_GEN;
CREATE GENERATOR PLAYER_GEN;
CREATE GENERATOR ROUND_GEN;
CREATE GENERATOR SEASON_GEN;
CREATE GENERATOR WEBUSER_GEN;
/******************** DOMAINS *********************/

/******************* PROCEDURES ******************/

/******************** TABLES **********************/

CREATE TABLE CHAMPIONSHIP
(
  ID Integer NOT NULL,
  IDSEASON Integer NOT NULL,
  NAME Varchar(128) NOT NULL,
  TYPE Integer NOT NULL,
  ISFINISHED Integer NOT NULL,
  BASEDATE Timestamp,
  DATEINCR Integer,
  ROUNDSBYDAY Integer,
  GAMESBYROUND Integer,
  CONSTRAINT PK_CHAMPIONSHIP PRIMARY KEY (ID),
  CONSTRAINT UNQ_CHAMPIONSHIP UNIQUE (IDSEASON, NAME)
);
CREATE TABLE CHAMPIONSHIPHISTORIC
(
  IDCHAMPIONSHIP Integer NOT NULL,
  IDROUND Integer NOT NULL,
  IDPLAYER Integer NOT NULL,
  POS Integer NOT NULL,
  CONSTRAINT UNQ_CH_PLAYER UNIQUE (IDCHAMPIONSHIP,IDROUND,IDPLAYER),
  CONSTRAINT UNQ_CH_POS UNIQUE (IDCHAMPIONSHIP,IDROUND,POS)
);
CREATE TABLE GAME
(
  ID Integer NOT NULL,
  IDROUND Integer NOT NULL,
  IDPLAYER1 Integer NOT NULL,
  IDPLAYER2 Integer NOT NULL,
  GAMETABLE Integer,
  SCORE1 Integer,
  SCORE2 Integer,
  INPUTDATE Timestamp,
  IDWINNER Integer,
  CONSTRAINT PK_GAME PRIMARY KEY (ID),
  CONSTRAINT UNQ_GAME UNIQUE (IDROUND,IDPLAYER1,IDPLAYER2)
);
CREATE TABLE GROUPS
(
  ID Integer NOT NULL,
  IDCHAMPIONSHIP Integer NOT NULL,
  IDPHASE Integer NOT NULL,
  NAME Varchar(128) NOT NULL,
  CONSTRAINT PK_GROUP PRIMARY KEY (ID),
  CONSTRAINT UNQ_GROUP UNIQUE (IDCHAMPIONSHIP,NAME)
);
CREATE TABLE GROUPMEMBERS
(
  IDGROUP Integer NOT NULL,
  IDPLAYER Integer NOT NULL,
  CONSTRAINT UNQ_GROUPMEMBER UNIQUE (IDGROUP,IDPLAYER)
);
CREATE TABLE GROUPROUNDS
(
  IDGROUP Integer NOT NULL,
  IDROUND Integer NOT NULL,
  CONSTRAINT UNQ_GROUPROUND UNIQUE (IDGROUP,IDROUND)
);
CREATE TABLE PARTICIPANT
(
  IDCHAMPIONSHIP Integer NOT NULL,
  IDPLAYER Integer NOT NULL
);
CREATE TABLE PHASE
(
  ID Integer NOT NULL,
  IDCHAMPIONSHIP Integer NOT NULL,
  TYPE Integer NOT NULL,
  NUMBER Integer NOT NULL,
  CONSTRAINT PK_PHASE PRIMARY KEY (ID),
  CONSTRAINT UNQ_PHASE UNIQUE (IDCHAMPIONSHIP,NUMBER)
);
CREATE TABLE PLAYER
(
  ID Integer NOT NULL,
  NAME Varchar(64) NOT NULL,
  CONSTRAINT PK_PLAYER PRIMARY KEY (ID),
  CONSTRAINT UNQ_PLAYER UNIQUE (NAME)
);
CREATE TABLE RANKING
(
  IDCHAMPIONSHIP Integer NOT NULL,
  IDPLAYER Integer NOT NULL,
  POINTS Integer NOT NULL,
  CONSTRAINT UNQ_RANKING UNIQUE (IDCHAMPIONSHIP,IDPLAYER)
);
CREATE TABLE ROUND
(
  ID Integer NOT NULL,
  IDCHAMPIONSHIP Integer NOT NULL,
  IDPHASE Integer NOT NULL,
  BASEDATE Date,
  BASEHOUR Time,
  NUMBER Integer NOT NULL,
  CONSTRAINT PK_ROUND PRIMARY KEY (ID),
  CONSTRAINT UNQ_ROUND UNIQUE (IDCHAMPIONSHIP,NUMBER)
);
CREATE TABLE SEASON
(
  ID Integer NOT NULL,
  NAME Varchar(64) NOT NULL,
  CONSTRAINT PK_SEASON PRIMARY KEY (ID),
  CONSTRAINT UNQ_SEASON UNIQUE (NAME)
);
CREATE TABLE WEBUSER
(
  ID Integer NOT NULL,
  NAME Varchar(128),
  LOGIN Varchar(128),
  PASS Varchar(256),
  ISADIMN INTEGER NOT NULL,
  CONSTRAINT PK_WEBUSER PRIMARY KEY (ID),
  CONSTRAINT UNQ_WEBUSER UNIQUE (LOGIN)
);
/********************* VIEWS **********************/

/******************* EXCEPTIONS *******************/

/******************** TRIGGERS ********************/

SET TERM ^ ;
CREATE TRIGGER CHAMPIONSHIP_BI FOR CHAMPIONSHIP ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(CHAMPIONSHIP_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(CHAMPIONSHIP_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(CHAMPIONSHIP_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER GAME_BI FOR GAME ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(GAME_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(GAME_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(GAME_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER GROUP_BI FOR GROUPS ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(GROUP_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(GROUP_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(GROUP_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER PHASE_BI FOR PHASE ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(PHASE_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(PHASE_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(PHASE_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER PLAYER_BI FOR PLAYER ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(PLAYER_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(PLAYER_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(PLAYER_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER ROUND_BI FOR ROUND ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(ROUND_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(ROUND_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(ROUND_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER SEASON_BI FOR SEASON ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(SEASON_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(SEASON_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(SEASON_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^
SET TERM ^ ;
CREATE TRIGGER WEBUSER_BI FOR WEBUSER ACTIVE
BEFORE INSERT POSITION 0
AS
DECLARE VARIABLE tmp DECIMAL(18,0);
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(WEBUSER_GEN, 1);
  ELSE
  BEGIN
    tmp = GEN_ID(WEBUSER_GEN, 0);
    if (tmp < new.ID) then
      tmp = GEN_ID(WEBUSER_GEN, new.ID-tmp);
  END
END^
SET TERM ; ^

ALTER TABLE CHAMPIONSHIP ADD CONSTRAINT FK_CH_SEASON
  FOREIGN KEY (IDSEASON) REFERENCES SEASON (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE CHAMPIONSHIPHISTORIC ADD CONSTRAINT FK_CH_ROUND
  FOREIGN KEY (IDROUND) REFERENCES ROUND (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE CHAMPIONSHIPHISTORIC ADD CONSTRAINT FK_CH_CHAMPIONSHIP
  FOREIGN KEY (IDCHAMPIONSHIP) REFERENCES CHAMPIONSHIP (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE CHAMPIONSHIPHISTORIC ADD CONSTRAINT FK_CH_PLAYER
  FOREIGN KEY (IDPLAYER) REFERENCES PLAYER (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GAME ADD CONSTRAINT FK_GAME_PLAYER1
  FOREIGN KEY (IDPLAYER1) REFERENCES PLAYER (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GAME ADD CONSTRAINT FK_GAME_PLAYER2
  FOREIGN KEY (IDPLAYER2) REFERENCES PLAYER (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GAME ADD CONSTRAINT FK_GAME_ROUND
  FOREIGN KEY (IDROUND) REFERENCES ROUND (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GROUPS ADD CONSTRAINT FK_GROUPS_CHAMP
  FOREIGN KEY (IDCHAMPIONSHIP) REFERENCES CHAMPIONSHIP (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GROUPS ADD CONSTRAINT FK_GROUPS_PHASE
  FOREIGN KEY (IDPHASE) REFERENCES PHASE (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GROUPMEMBERS ADD CONSTRAINT FK_MEMBERS_GROUP
  FOREIGN KEY (IDGROUP) REFERENCES GROUPS (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GROUPMEMBERS ADD CONSTRAINT FK_MEMBERS_PLAYER
  FOREIGN KEY (IDPLAYER) REFERENCES PLAYER (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GROUPROUNDS ADD CONSTRAINT FK_ROUNDS_GROUP
  FOREIGN KEY (IDGROUP) REFERENCES GROUPS (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE GROUPROUNDS ADD CONSTRAINT FK_ROUNDS_ROUND
  FOREIGN KEY (IDROUND) REFERENCES ROUND (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PARTICIPANT ADD CONSTRAINT FK_PARTICIPANT_CHAMP
  FOREIGN KEY (IDCHAMPIONSHIP) REFERENCES CHAMPIONSHIP (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PARTICIPANT ADD CONSTRAINT FK_PARTICIPANT_PLAYER
  FOREIGN KEY (IDPLAYER) REFERENCES PLAYER (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PHASE ADD CONSTRAINT FK_PHASE_CHAMP
  FOREIGN KEY (IDCHAMPIONSHIP) REFERENCES CHAMPIONSHIP (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE RANKING ADD CONSTRAINT FK_RANKING_CHAMP
  FOREIGN KEY (IDCHAMPIONSHIP) REFERENCES CHAMPIONSHIP (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE RANKING ADD CONSTRAINT FK_RANKING_PLAYER
  FOREIGN KEY (IDPLAYER) REFERENCES PLAYER (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ROUND ADD CONSTRAINT FK_ROUND_CHAMP
  FOREIGN KEY (IDCHAMPIONSHIP) REFERENCES CHAMPIONSHIP (ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ROUND ADD CONSTRAINT FK_ROUND_PHASE
  FOREIGN KEY (IDPHASE) REFERENCES PHASE (ID) ON UPDATE CASCADE ON DELETE CASCADE;
GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON CHAMPIONSHIP TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON CHAMPIONSHIPHISTORIC TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON GAME TO  SYSDBA WITH GRANT OPTION;
 
GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON GROUPS TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON GROUPMEMBERS TO  SYSDBA WITH GRANT OPTION;
 
GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON GROUPROUNDS TO  SYSDBA WITH GRANT OPTION;
 
GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON PARTICIPANT TO  SYSDBA WITH GRANT OPTION;
 
GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON PHASE TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON PLAYER TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON RANKING TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON ROUND TO  SYSDBA WITH GRANT OPTION;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON SEASON TO  SYSDBA WITH GRANT OPTION;
 
GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON WEBUSER TO  SYSDBA WITH GRANT OPTION;

