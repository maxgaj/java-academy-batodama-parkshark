INSERT INTO BASE_USER VALUES (BASE_USER_SEQUENCE.NEXTVAL, 'member', '1234', 'ROLE_MEMBER');
INSERT INTO MEMBER VALUES (1, 'member', 'parkshark', 'central street 42', '1000', 'Brussels', 'Belgium', 'member.parkshark@local.be', '0123456789', '1ABC123', 'Belgium', TO_DATE('2019/09/02 09:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'SILVER');

INSERT INTO BASE_USER VALUES (BASE_USER_SEQUENCE.NEXTVAL, 'manager', '1234', 'ROLE_MANAGER');
INSERT INTO MANAGER VALUES (2, 'manager', 'parkshark');

INSERT INTO DIVISION(ID,NAME,ORIGINAL_NAME,DIRECTOR_FIRST_NAME,DIRECTOR_LAST_NAME,PARENT_ID)
VALUES (DIVISION_SEQUENCE.NEXTVAL, 'Main division', 'Red', 'John', 'Jenkins', null );
INSERT INTO DIVISION(ID,NAME,ORIGINAL_NAME,DIRECTOR_FIRST_NAME,DIRECTOR_LAST_NAME,PARENT_ID)
VALUES (DIVISION_SEQUENCE.NEXTVAL, 'Subdivision', 'Blue', 'John', 'Jenkins',1);

INSERT INTO PARKING_LOT_CONTACT_PERSON(ID, NAME, E_MAIL, PHONE_NUMBER, TELEPHONE_NUMBER, STREET_NAME, STREET_NUMBER, POST_CODE, POST_LABEL)
VALUES (PARKING_LOT_CONTACT_PERSON_SEQUENCE.NEXTVAL, 'Kevin', 'kevin@hotmail.com', '0474559632', NULL, 'Street One', '25', '1000', 'Brussels');
INSERT INTO PARKING_LOT_CONTACT_PERSON(ID, NAME, E_MAIL, PHONE_NUMBER, TELEPHONE_NUMBER, STREET_NAME, STREET_NUMBER, POST_CODE, POST_LABEL)
VALUES (PARKING_LOT_CONTACT_PERSON_SEQUENCE.NEXTVAL, 'David', 'david@hotmail.com', '0851525589', NULL, 'Street Two', '99', '3000', 'Leuven');

INSERT INTO PARKING_LOT(ID, PARKING_NAME, PARKING_CATEGORY, STREET_NAME, STREET_NUMBER, POST_CODE, POST_LABEL, PARKING_MAX_SIZE, ALLOCATION_PRICE_PER_HOUR, CONTACT_PERSON_ID,DIVISION_ID)
VALUES (PARKING_LOT_SEQUENCE.NEXTVAL, 'Parking One', 'ABOVE_GROUND', 'Street One', '99', '3000', 'Leuven', 50, 25, 2,1);
INSERT INTO PARKING_LOT(ID, PARKING_NAME, PARKING_CATEGORY, STREET_NAME, STREET_NUMBER, POST_CODE, POST_LABEL, PARKING_MAX_SIZE, ALLOCATION_PRICE_PER_HOUR, CONTACT_PERSON_ID,DIVISION_ID)
VALUES (PARKING_LOT_SEQUENCE.NEXTVAL, 'Parking Two', 'UNDERGROUND', 'Street Two', '25', '1000', 'Brussels', 25, 10, 1,2);