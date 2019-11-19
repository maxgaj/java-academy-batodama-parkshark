CREATE TABLE DIVISION
(
    ID                  NUMBER,
    NAME                VARCHAR2(50) UNIQUE NOT NULL,
    ORIGINAL_NAME       VARCHAR2(50)        NOT NULL,
    DIRECTOR_FIRST_NAME VARCHAR2(50)        NOT NULL,
    DIRECTOR_LAST_NAME  VARCHAR2(50)        NOT NULL,
    PARENT_ID NUMBER,
    CONSTRAINT PK_DIVISION PRIMARY KEY (ID),
    CONSTRAINT FK_PARENT FOREIGN KEY (ID) REFERENCES DIVISION (ID)
);

CREATE SEQUENCE DIVISION_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE BASE_USER
(
    ID NUMBER,
    USERNAME VARCHAR2(50) NOT NULL,
    PASSWORD VARCHAR2(50) NOT NULL,
    ROLE VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_BASE_USER PRIMARY KEY (ID)
);
CREATE SEQUENCE BASE_USER_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE MEMBER
(
    ID                  NUMBER,
    FIRST_NAME          VARCHAR2(50)        NOT NULL,
    LAST_NAME           VARCHAR2(50)        NOT NULL,
    STREET_AND_NUMBER   VARCHAR2(50)        NOT NULL,
    ZIP_CODE            VARCHAR2(15)        NOT NULL,
    CITY                VARCHAR2(50)        NOT NULL,
    COUNTRY             VARCHAR2(50)        NOT NULL,
    EMAIL               VARCHAR2(50)        NOT NULL,
    PHONE               VARCHAR2(15)        NOT NULL,
    LICENCE_PLATE_NUMBER    VARCHAR2(15)    NOT NULL,
    LICENCE_PLATE_COUNTRY   VARCHAR2(50)    NOT NULL,
    REGISTRATION_DATE   DATE                NOT NULL,
    CONSTRAINT  PK_MEMBER PRIMARY KEY (ID),
    CONSTRAINT FK_MEMBER_BU FOREIGN KEY (ID) references BASE_USER(ID)
);

CREATE TABLE MANAGER
(
    ID                  NUMBER,
    FIRST_NAME          VARCHAR2(50)        NOT NULL,
    LAST_NAME           VARCHAR2(50)        NOT NULL,
    CONSTRAINT  PK_MANAGER PRIMARY KEY (ID),
    CONSTRAINT FK_MANAGER_BU FOREIGN KEY (ID) references BASE_USER(ID)
);

CREATE TABLE PARKING_LOT_CONTACT_PERSON
(
    ID                      NUMBER,
    NAME                    VARCHAR2(50)        NOT NULL,
    E_MAIL                  VARCHAR2(50)        NOT NULL,
    PHONE_NUMBER            VARCHAR2(50)        NOT NULL,
    TELEPHONE_NUMBER        VARCHAR2(50),
    STREET_NAME             VARCHAR2(50)        NOT NULL,
    STREET_NUMBER           VARCHAR2(50)        NOT NULL,
    POST_CODE               VARCHAR2(50)        NOT NULL,
    POST_LABEL              VARCHAR2(50)        NOT NULL,
    CONSTRAINT PK_PARKING_LOT_CONTACT_PERSON PRIMARY KEY (ID)
);

CREATE SEQUENCE PARKING_LOT_CONTACT_PERSON_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE PARKING_LOT
(
    ID                          NUMBER,
    PARKING_NAME                VARCHAR2(50)        NOT NULL,
    PARKING_CATEGORY            VARCHAR2(50)        NOT NULL,
    STREET_NAME                 VARCHAR2(50)        NOT NULL,
    STREET_NUMBER               VARCHAR2(50)        NOT NULL,
    POST_CODE                   VARCHAR2(50)        NOT NULL,
    POST_LABEL                  VARCHAR2(50)        NOT NULL,
    PARKING_MAX_SIZE            VARCHAR2(50)        NOT NULL,
    ALLOCATION_PRICE_PER_HOUR   NUMBER              NOT NULL,
    CONTACT_PERSON_ID           NUMBER,
    CONSTRAINT FK_PARKING_LOT_CONTACT_PERSON_ID FOREIGN KEY (CONTACT_PERSON_ID) references PARKING_LOT_CONTACT_PERSON (ID),
    CONSTRAINT PK_PARKING_LOT PRIMARY KEY (ID)
);

CREATE SEQUENCE PARKING_LOT_SEQUENCE START WITH 3 INCREMENT BY 1;
