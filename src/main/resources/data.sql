USE test312;

INSERT INTO user VALUES ('1','11','Jjj','1','jjjov','user1');
INSERT INTO user VALUES ('2','22','Qqq','1','Qqqov','user2');
INSERT INTO user VALUES ('3','22','Www','1','Wwwov','user3');
INSERT INTO user VALUES ('4','44','Vvv','$2a$10$rfUczXcy3gmhT2Hft.ewI.jrK3JtBNVs0z7BLgx4x15xuYHI95mg6','Vvvov','user4');
INSERT INTO user VALUES ('5','55','Aaa' ,'$2a$10$rfUczXcy3gmhT2Hft.ewI.jrK3JtBNVs0z7BLgx4x15xuYHI95mg6','Aaaov','user5');

INSERT INTO role VALUES ('1', 'ROLE_USER');
INSERT INTO role VALUES ('2', 'ROLE_ADMIN');


INSERT INTO user_role VALUES ('1', '1');
INSERT INTO user_role VALUES ('2', '1');
INSERT INTO user_role VALUES ('3', '1');
INSERT INTO user_role VALUES ('4', '1');
INSERT INTO user_role VALUES ('5', '2');




