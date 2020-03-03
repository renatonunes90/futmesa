﻿INSERT INTO PLAYER (NAME)
 VALUES ( 'Nelson' );

 INSERT INTO SEASON (NAME)
 VALUES ( '2020' );
 
INSERT INTO CHAMPIONSHIP (IDSEASON, NAME, TYPE, ISFINISHED)
 VALUES ( (SELECT ID FROM SEASON WHERE NAME='2020'), 'Trofeu Abertura 2020', 1, 0 );


INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Renato Barbieri') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Nei Barbieri') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Cristian Costa') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Cristiano Carneiro') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='FC') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Jeferson Lucas') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Luzardo Carpes') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Mano') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Mauro') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Roberto Bopp') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Robin Antonelo') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Thiago Leao') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Val') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Xico') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT ID FROM PLAYER WHERE NAME='Nelson') );

 
INSERT INTO PHASE (IDCHAMPIONSHIP, TYPE, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), 1, 1 );

 
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/10/2020', '20:00', 1 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/10/2020', '21:00', 2 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/17/2020', '20:00', 3 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/17/2020', '21:00', 4 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/24/2020', '20:00', 5 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/24/2020', '21:00', 6 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/31/2020', '20:00', 7 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '03/31/2020', '21:00', 8 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '04/07/2020', '20:00', 9 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '04/07/2020', '21:00', 10 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '04/14/2020', '20:00', 11 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '04/14/2020', '21:00', 12 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '04/28/2020', '20:00', 13 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '04/28/2020', '21:00', 14 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '05/05/2020', '20:00', 15 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '05/05/2020', '21:00', 16 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '05/12/2020', '19:30', 17 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Abertura 2020'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Abertura 2020' AND P.NUMBER = 1), '05/12/2020', '20:30', 18 );
