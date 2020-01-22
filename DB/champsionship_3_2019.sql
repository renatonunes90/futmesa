﻿INSERT INTO CHAMPIONSHIP (IDSEASON, NAME, TYPE, ISFINISHED)
 VALUES ( (SELECT ID FROM SEASON WHERE NAME='2019'), 'Trofeu Encerramento 2019', 1, 0 );


INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Renato Barbieri') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Nei Barbieri') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Cristian Costa') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Cristiano Carneiro') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='FC') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Jeferson Lucas') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Jorge Macedo') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Luzardo Carpes') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Mano') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Mauro') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Otavio Germano') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Ricardo Gothe') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Roberto Bopp') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Robin Antonelo') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Thiago Leao') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Val') );
INSERT INTO PARTICIPANT (IDCHAMPIONSHIP, IDPLAYER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT ID FROM PLAYER WHERE NAME='Xico') );

 
INSERT INTO PHASE (IDCHAMPIONSHIP, TYPE, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), 1, 1 );
INSERT INTO PHASE (IDCHAMPIONSHIP, TYPE, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), 3, 2 );

 
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/06/2019', '20:00', 1 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/06/2019', '21:00', 2 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/13/2019', '20:00', 3 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/13/2019', '21:00', 4 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/22/2019', '20:00', 5 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/22/2019', '21:00', 6 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/29/2019', '20:00', 7 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '08/29/2019', '21:00', 8 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/03/2019', '20:00', 9 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/03/2019', '21:00', 10 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/10/2019', '20:00', 11 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/10/2019', '21:00', 12 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/17/2019', '20:00', 13 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/17/2019', '21:00', 14 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/24/2019', '20:00', 15 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '09/24/2019', '21:00', 16 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/01/2019', '20:00', 17 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/01/2019', '21:00', 18 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/05/2019', '13:30', 19 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/05/2019', '14:30', 20 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/08/2019', '20:00', 21 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/08/2019', '21:00', 22 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/15/2019', '20:00', 23 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 1), '10/15/2019', '21:00', 24 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 2), '10/29/2019', '19:30', 25 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 2), '10/29/2019', '20:30', 26 );
INSERT INTO ROUND ( IDCHAMPIONSHIP, IDPHASE, BASEDATE, BASEHOUR, NUMBER)
 VALUES ( (SELECT ID FROM CHAMPIONSHIP WHERE NAME='Trofeu Encerramento 2019'), (SELECT P.ID FROM PHASE P JOIN CHAMPIONSHIP C ON C.ID = P.IDCHAMPIONSHIP WHERE C.NAME='Trofeu Encerramento 2019' AND P.NUMBER = 2), '10/29/2019', '21:30', 27 );
 