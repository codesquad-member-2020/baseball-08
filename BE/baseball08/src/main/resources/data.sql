------- GAME -------
INSERT INTO game (id) VALUES (1);
INSERT INTO game (id) VALUES (2);
INSERT INTO game (id) VALUES (3);

------- TEAM -------
INSERT INTO team (name,game,game_key,pitcher,current_hitter) VALUES ('기아 타이거즈',1,0,'터너','맥닐');
INSERT INTO team (name,game,game_key,pitcher,current_hitter) VALUES ('삼성 라이온즈',1,1,'카스테야노','몬카다');
INSERT INTO team (name,game,game_key,pitcher,current_hitter) VALUES ('한화 이글스',2,0,'이글레시아스','아레나도');
INSERT INTO team (name,game,game_key,pitcher,current_hitter) VALUES ('NC 다이노스',2,1,'라모스','블랙몬');
INSERT INTO team (name,game,game_key,user_id,pitcher,current_hitter) VALUES ('롯데 자이언츠',3,0,'honux','로사리오','레이놀즈');
INSERT INTO team (name,game,game_key,pitcher,current_hitter) VALUES ('코쿼 호눅스',3,1,'랙돌','헨리');

------- INNING -------
INSERT INTO inning (away_name,home_name,game,game_key) VALUES ('기아 타이거즈','삼성 라이온즈',1,0);
INSERT INTO inning (away_name,home_name,game,game_key) VALUES ('한화 이글스','NC 다이노스',2,0);
INSERT INTO inning (away_name,home_name,game,game_key) VALUES ('롯데 자이언츠','코쿼 호눅스',3,0);

------- PLAYER -------
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('맥닐',0.317,1,1,1);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('몬카다',0.318,2,1,1);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('아레나도',0.319,3,1,1);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('블랙몬',0.320,4,1,1);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('레이놀즈',0.321,5,1,1);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('브랜틀리',0.322,1,2,2);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('디버스',0.323,2,2,2);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('크루즈',0.324,3,2,2);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('보가츠',0.325,4,2,2);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('뉴먼',0.326,5,2,2);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('알베르토',0.327,1,3,3);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('벨린저',0.328,2,3,3);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('마르티네스',0.329,3,3,3);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('메리필드',0.330,4,3,3);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('알튜베',0.331,5,3,3);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('구리엘',0.332,1,4,4);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('터미네이터',0.333,2,4,4);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('브렉먼',0.334,3,4,4);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('알비스',0.335,4,4,4);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('마르테',0.336,5,4,4);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('베츠',0.337,1,5,5);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('프리먼',0.338,2,5,5);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('폴랑코',0.339,3,5,5);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('스토리',0.340,4,5,5);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('리조',0.341,5,5,5);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('스프링어',0.342,1,6,6);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('트라웃',0.343,2,6,6);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('만치니',0.344,3,6,6);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('메도우즈',0.345,4,6,6);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('플레처',0.346,5,6,6);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('터너',0.347,1,7,7);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('카스테야노스',0.348,2,7,7);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('이글레시아스',0.349,3,7,7);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('라모스',0.350,4,7,7);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('로사리오',0.351,5,7,7);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('브론',0.352,1,8,8);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('세미엔',0.353,2,8,8);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('웡',0.354,3,8,8);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('린도어',0.355,4,8,8);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('아브레유',0.356,5,8,8);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('로하스',0.357,1,9,9);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('산타나',0.358,2,9,9);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('소토',0.359,3,9,9);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('가르시아',0.360,4,9,9);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('카브레라',0.361,5,9,9);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('헨리',0.362,6,1,1);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('젤로',0.363,6,2,2);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('강운',0.364,6,3,3);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('알렉스',0.365,6,4,4);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('호눅스',0.366,6,5,5);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('하밀',0.367,6,6,6);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('랙돌',0.368,6,7,7);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('모카',0.369,6,8,8);
INSERT INTO player (name,average,team,team_key,line_up) VALUES ('엘리',0.370,6,9,9);
