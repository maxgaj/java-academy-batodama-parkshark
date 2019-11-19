INSERT INTO BASE_USER VALUES (BASE_USER_SEQUENCE.NEXTVAL, 'member', '1234', 'ROLE_MEMBER');
INSERT INTO MEMBER VALUES (1, 'member', 'parkshark', 'central street 42', '1000', 'Brussels', 'Belgium', 'member.parkshark@local.be', '0123456789', '1ABC123', 'Belgium', TO_DATE('2019/09/02 09:00:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO BASE_USER VALUES (BASE_USER_SEQUENCE.NEXTVAL, 'manager', '1234', 'ROLE_MANAGER');
INSERT INTO MANAGER VALUES (2, 'manager', 'parkshark');